package com.baizhi.ysk.service;

import com.baizhi.ysk.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface ChapterService {
    void addChapter(Chapter chapter, MultipartFile file, HttpSession session) throws IOException;

    void download(String name, HttpSession session, HttpServletResponse response) throws IOException;
}
