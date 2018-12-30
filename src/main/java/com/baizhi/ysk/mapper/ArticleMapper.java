package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    List<Article> queryAllArticleBySsyj(@Param("uid") Integer uid, @Param("subType") String subType);

    List<Article> queryAllArticleByXmfy(@Param("uid") Integer uid, @Param("subType") String subType);
}
