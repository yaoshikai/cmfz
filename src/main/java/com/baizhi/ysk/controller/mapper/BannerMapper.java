package com.baizhi.ysk.controller.mapper;

import com.baizhi.ysk.controller.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    List<Banner> queryAllBanner(@Param("page") Integer page, @Param("rows") Integer rows);
}
