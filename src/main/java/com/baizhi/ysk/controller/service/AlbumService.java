package com.baizhi.ysk.controller.service;

import com.baizhi.ysk.controller.dto.Dto;
import com.baizhi.ysk.controller.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AlbumService {
    Dto<Album> queryAllAlbum(Integer page, Integer rows);

    Album queryOneAlbum(Integer id);

    void addAlbum(Album album, MultipartFile file, HttpSession session);

    void exportAlbum(HttpServletResponse response, HttpSession session);
}
