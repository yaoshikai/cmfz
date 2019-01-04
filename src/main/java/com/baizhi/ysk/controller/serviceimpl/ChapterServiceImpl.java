package com.baizhi.ysk.controller.serviceimpl;

import com.baizhi.ysk.controller.entity.Album;
import com.baizhi.ysk.controller.entity.Chapter;
import com.baizhi.ysk.controller.mapper.AlbumMapper;
import com.baizhi.ysk.controller.mapper.ChapterMapper;
import com.baizhi.ysk.controller.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FilenameUtils;
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
    public void addChapter(Chapter chapter, MultipartFile file, HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("audio");
        String originalFilename = file.getOriginalFilename();

        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdir();
        }


        /*给属性赋值*/
        String uuid = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(uuid);
        /*给文件重命名*/
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = uuid + "." + extension;
        chapter.setUrl(newName);
        chapter.setUploadDate(new Date());

        /*将该文件存到硬盘*/
        try {
            file.transferTo(new File(realPath + "/" + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*获取文件大小*/
        Long fileSize = file.getSize();
        Double d = Double.valueOf(fileSize.toString());
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d / 1024 / 1024);

        /*获取时长*/
        File f = new File(realPath + "/" + newName);
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


        chapter.setSize(format + "MB");
        chapter.setDuration(time);
        /*入库*/
        chapterMapper.insert(chapter);

        /*更改专辑的集数*/
        Integer albumId = chapter.getAlbumId();
        Album album = albumMapper.selectByPrimaryKey(albumId);
        Integer count = album.getCount() + 1;

        Album a = new Album();
        a.setId(albumId);
        a.setCount(count);

        albumMapper.updateByPrimaryKeySelective(a);
    }

    @Override
    public void download(String name, String title, HttpSession session, HttpServletResponse response) {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("audio");

        String extension = FilenameUtils.getExtension(name);

        ServletOutputStream outputStream = null;
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(new File(realPath + "/" + name));
            response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(title + "." + extension, "UTF-8"));
            response.setContentType("audio/mpeg");
            outputStream = response.getOutputStream();

            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
