package com.baizhi.ysk.indexservice;

import com.baizhi.ysk.entity.Article;

import java.util.List;

public interface IndexArticleService {
    List<Article> queryArticleIndex(String params);
}
