package com.baizhi.ysk.service;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Article;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {
    Dto<Article> queryAllArticle(Integer page, Integer rows);

    void addArticle(Article article, MultipartFile file);
}
