package com.baizhi.ysk.controller.dto;

import com.baizhi.ysk.controller.entity.Album;
import com.baizhi.ysk.controller.entity.Article;
import com.baizhi.ysk.controller.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstPageDto implements Serializable {
    private List<Banner> header;
    private List<Album> album;
    private List<Article> article;
}
