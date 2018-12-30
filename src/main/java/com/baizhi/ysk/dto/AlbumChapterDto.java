package com.baizhi.ysk.dto;

import com.baizhi.ysk.entity.Album;
import com.baizhi.ysk.entity.Chapter;
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
