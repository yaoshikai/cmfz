package com.baizhi.ysk.controller;

import com.baizhi.ysk.entity.Chapter;
import com.baizhi.ysk.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    public void addChapter(Chapter chapter, MultipartFile file) {
        chapterService.addChapter(chapter, file);
    }

    @RequestMapping("/download")
    public void download(String name, String title, HttpServletResponse response) {
        chapterService.download(name, title, response);
    }
}
