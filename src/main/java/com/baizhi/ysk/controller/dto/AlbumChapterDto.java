package com.baizhi.ysk.controller.dto;

import com.baizhi.ysk.controller.entity.Album;
import com.baizhi.ysk.controller.entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumChapterDto implements Serializable {
    private Album introduction;
    private List<Chapter> list;
}
