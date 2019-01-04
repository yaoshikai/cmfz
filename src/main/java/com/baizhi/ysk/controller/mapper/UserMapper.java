package com.baizhi.ysk.controller.mapper;

import com.baizhi.ysk.controller.dto.Province;
import com.baizhi.ysk.controller.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    Integer queryActiveUser(@Param("condition") String condition);

    List<Province> queryDistributionUserM();

    List<Province> queryDistributionUserF();
}
