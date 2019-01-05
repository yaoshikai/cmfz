package com.baizhi.ysk.mapper;

import com.baizhi.ysk.entity.Admin;
import com.baizhi.ysk.entity.Limits;
import com.baizhi.ysk.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdminMapper extends Mapper<Admin> {
    List<Role> queryRolesByName(String name);

    List<Limits> queryLimitsById(Integer id);
}
