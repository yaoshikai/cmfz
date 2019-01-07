package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.entity.Chapter;
import com.baizhi.ysk.mapper.AlbumMapper;
import com.baizhi.ysk.mapper.ChapterMapper;
import com.baizhi.ysk.service.ChapterService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @Override
    public void addChapter(Chapter chapter, MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*给属性赋值*/
        String uuid = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(uuid);
        chapter.setUrl(storePath.getFullPath());
        chapter.setUploadDate(new Date());

        /*获取文件大小*/
        Long fileSize = file.getSize();
        Double d = Double.valueOf(fileSize.toString());
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d / 1024 / 1024);

        /*获取时长*/

        File f = new File("D:/audio/" + file.getOriginalFilename());
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
    public void download(String name, String title, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        try {
            String[] ss = name.split("/", 2);
            byte[] bytes = fastFileStorageClient.downloadFile(ss[0], ss[1], new DownloadByteArray());
            response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(title + "." + FilenameUtils.getExtension(name), "UTF-8"));
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
