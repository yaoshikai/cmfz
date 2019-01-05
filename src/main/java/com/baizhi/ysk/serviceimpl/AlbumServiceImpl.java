package com.baizhi.ysk.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.mapper.AlbumMapper;
import com.baizhi.ysk.service.AlbumService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Log4j
public class AlbumServiceImpl implements AlbumService {
    private static Integer num = 0;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private Dto<Album> dto;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Dto<Album> queryAllAlbum(Integer page, Integer rows) {
        List<Album> list = albumMapper.queryAllAlbum(page, rows);
        dto.setRows(list);
        Integer count = albumMapper.selectCount(null);
        dto.setTotal(count);
        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Album queryOneAlbum(Integer id) {
        Album album = albumMapper.selectByPrimaryKey(id);
        return album;
    }

    @Override
    public void addAlbum(Album album, MultipartFile file, HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("album-cover");
        String originalFilename = file.getOriginalFilename();
        File f = new File(realPath + "/" + originalFilename);

        album.setCoverImg(originalFilename);
        album.setCount(0);
        album.setScore(0);
        album.setPubDate(new Date());

        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        albumMapper.insert(album);
    }

    @Override
    public void exportAlbum(HttpServletResponse response, HttpSession session) {
        Integer count = albumMapper.selectCount(null);
        List<Album> albums = albumMapper.queryAllAlbum(1, count);
        for (Album album : albums) {
            String realPath = session.getServletContext().getRealPath("/album-cover");
            String s = realPath + "/" + album.getCoverImg();
            album.setCoverImg(s);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑总览表", "专辑"), Album.class, albums);
        OutputStream outputStream = null;
        try {
            String extension = FilenameUtils.getExtension("专辑.xls");
            String baseName = FilenameUtils.getBaseName("专辑.xls");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(baseName + (++num) + "." + extension, "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
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
