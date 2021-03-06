package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    List<Album> queryAllAlbum(@Param("page") Integer page, @Param("rows") Integer rows);

    Album queryOneAlbum(Integer id);
}
