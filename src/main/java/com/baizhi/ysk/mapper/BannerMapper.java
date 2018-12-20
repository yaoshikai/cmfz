package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    List<Banner> queryAllBanner(@Param("page") Integer page, @Param("rows") Integer rows);
}
