package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.mapper.AlbumMapper;
import com.baizhi.ysk.mapper.ChapterMapper;
import com.baizhi.ysk.service.AlbumService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Log4j
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private Dto<Album> dto;
    @Autowired
    private ChapterMapper chapterMapper;

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
    public void addAlbum(Album album, MultipartFile file, HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("album-cover");
        String originalFilename = file.getOriginalFilename();
        File f = new File(realPath + "/" + originalFilename);

        album.setCoverImg(originalFilename);
        album.setCount(0);
        album.setScore(0);
        album.setPubDate(new Date());

        file.transferTo(f);

        albumMapper.insert(album);
    }

}
