package com.baizhi.ysk.mapper;

import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    Integer queryActiveUser(@Param("condition") String condition);

    List<Province> queryDistributionUserM();

    List<Province> queryDistributionUserF();
}
