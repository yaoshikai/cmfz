package com.baizhi.ysk.indexdao;

import com.baizhi.ysk.entity.Article;

import java.util.List;

public interface IndexArticleDao {
    List<Article> queryArticleIndex(String params);
}
