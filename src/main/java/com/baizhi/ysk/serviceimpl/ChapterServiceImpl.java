package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.entity.Chapter;
import com.baizhi.ysk.mapper.AlbumMapper;
import com.baizhi.ysk.mapper.ChapterMapper;
import com.baizhi.ysk.service.ChapterService;
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
        Long fileSize = file.getSize();
        Double d = Double.valueOf(fileSize.toString());
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d / 1024 / 1024);

        File f = new File(realPath + "/" + originalFilename);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(uuid);
        chapter.setUrl(originalFilename);
        chapter.setUploadDate(new Date());
        chapter.setSize(format + "MB");
        chapter.setDuration("20min");

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
