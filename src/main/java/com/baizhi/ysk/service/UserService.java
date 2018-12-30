package com.baizhi.ysk.service;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<Integer> queryActiveUser(String condition1, String condition2, String condition3);

    Map<String, List<Province>> queryDistributionUser(String sex, String sex2);

    Dto<User> queryAllUser(Integer page, Integer rows);

    void updateUser(User user);

    void exportUser(HttpServletResponse response, HttpSession session);

    Object changeUser(User user);

    Object login(String phone, String password, String code);

    Object regist(String phone, String password);
}
