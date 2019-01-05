package com.baizhi.ysk.dto;

import com.baizhi.ysk.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto implements Serializable {
    private List<Article> article;
}
