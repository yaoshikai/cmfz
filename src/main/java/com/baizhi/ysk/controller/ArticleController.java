package com.baizhi.ysk.controller;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Article;
import com.baizhi.ysk.indexservice.IndexArticleService;
import com.baizhi.ysk.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private IndexArticleService indexArticleService;


    @RequestMapping("/queryAllArticle")
    public Dto<Article> queryAllArticle(Integer page, Integer rows) {
        Dto<Article> dto = articleService.queryAllArticle(page, rows);
        return dto;
    }


    @RequestMapping("/queryArticleIndex")
    public List<Article> queryArticleIndex(String params) {
        List<Article> articles = indexArticleService.queryArticleIndex(params);
        return articles;
    }


    @RequestMapping("/addArticle")
    public void addArticle(Article article, MultipartFile file) {
        articleService.addArticle(article, file);
    }
}
