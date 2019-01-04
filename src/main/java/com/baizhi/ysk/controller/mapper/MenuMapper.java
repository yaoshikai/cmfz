package com.baizhi.ysk.controller.mapper;

import com.baizhi.ysk.controller.entity.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    List<Menu> queryAll();
}
