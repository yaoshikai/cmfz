package com.baizhi.ysk.controller;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public void addAlbum(Album album, MultipartFile file, HttpSession session) throws IOException {
        albumService.addAlbum(album, file, session);
    }


}
