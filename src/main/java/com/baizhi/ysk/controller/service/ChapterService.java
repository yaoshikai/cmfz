package com.baizhi.ysk.controller.service;

import com.baizhi.ysk.controller.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ChapterService {
    void addChapter(Chapter chapter, MultipartFile file, HttpSession session);

    void download(String name, String title, HttpSession session, HttpServletResponse response);
}
