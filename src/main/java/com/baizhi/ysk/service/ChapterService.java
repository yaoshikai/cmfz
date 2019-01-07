package com.baizhi.ysk.service;

import com.baizhi.ysk.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ChapterService {
    void addChapter(Chapter chapter, MultipartFile file);

    void download(String name, String title, HttpServletResponse response);
}
