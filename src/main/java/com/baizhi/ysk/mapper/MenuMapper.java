package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    List<Menu> queryAll();
}
