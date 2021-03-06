package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    List<Article> queryAllArticleBySsyj(Integer uid);

    List<Article> queryAllArticleByXmfy(Integer uid);

    List<Article> queryAllArticle(@Param("page") Integer page, @Param("rows") Integer rows);
}
