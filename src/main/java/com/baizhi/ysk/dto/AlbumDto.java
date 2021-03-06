package com.baizhi.ysk.dto;

import com.baizhi.ysk.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto implements Serializable {
    private List<Album> album;
}
