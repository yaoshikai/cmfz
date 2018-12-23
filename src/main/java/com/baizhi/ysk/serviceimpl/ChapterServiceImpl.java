package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.entity.Chapter;
import com.baizhi.ysk.mapper.AlbumMapper;
import com.baizhi.ysk.mapper.ChapterMapper;
import com.baizhi.ysk.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@Log4j
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void addChapter(Chapter chapter, MultipartFile file, HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("audio");
        String originalFilename = file.getOriginalFilename();

        /*获取文件大小*/
        Long fileSize = file.getSize();
        Double d = Double.valueOf(fileSize.toString());
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d / 1024 / 1024);

        /*获取时长*/
        File f = new File(realPath + "/" + originalFilename);
        Encoder encoder = new Encoder();
        MultimediaInfo info = null;
        try {
            info = encoder.getInfo(f);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        long l = info.getDuration();
        String time = "";
        if (l / 60000 != 0) {
            time = l / 60000 + "分" + (l % 60000) / 1000 + "秒";
        } else {
            time = (l % 60000) / 1000 + "秒";
        }


        String uuid = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(uuid);
        chapter.setUrl(originalFilename);
        chapter.setUploadDate(new Date());
        chapter.setSize(format + "MB");
        chapter.setDuration(time);

        chapterMapper.insert(chapter);

        Integer albumId = chapter.getAlbumId();
        Album album = albumMapper.selectByPrimaryKey(albumId);
        Integer count = album.getCount() + 1;

        Album a = new Album();
        a.setId(albumId);
        a.setCount(count);

        albumMapper.updateByPrimaryKeySelective(a);

        file.transferTo(f);

    }

    @Override
    public void download(String name, HttpSession session, HttpServletResponse response) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("audio");
        byte[] bytes = FileCopyUtils.copyToByteArray(new File(realPath + "/" + name));

        response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(name, "UTF-8"));

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(bytes);
        if (outputStream != null) outputStream.flush();
        if (outputStream != null) outputStream.close();
    }
}
