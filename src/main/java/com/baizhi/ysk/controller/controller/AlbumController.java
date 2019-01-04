package com.baizhi.ysk.controller.controller;

import com.baizhi.ysk.controller.dto.Dto;
import com.baizhi.ysk.controller.entity.Album;
import com.baizhi.ysk.controller.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryAllAlbum")
    public Dto<Album> queryAllAlbum(Integer page, Integer rows) {
        Dto<Album> dto = albumService.queryAllAlbum(page, rows);
        return dto;
    }

    @RequestMapping("/queryOneAlbum")
    public Album queryOneAlbum(Integer id) {
        Album album = albumService.queryOneAlbum(id);
        return album;
    }

    @RequestMapping("/addAlbum")
    public void addAlbum(Album album, MultipartFile file, HttpSession session) {
        albumService.addAlbum(album, file, session);
    }

    @RequestMapping("/exportAlbum")
    public void exportAlbum(HttpServletResponse response, HttpSession session) {
        albumService.exportAlbum(response, session);
    }


}
