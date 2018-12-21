package com.baizhi.ysk.service;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface AlbumService {
    Dto<Album> queryAllAlbum(Integer page, Integer rows);

    Album queryOneAlbum(Integer id);

    void addAlbum(Album album, MultipartFile file, HttpSession session) throws IOException;
}
