package com.baizhi.ysk.controller;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;
import com.baizhi.ysk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryActiveUser")
    public List<Integer> queryActiveUser(String condition1, String condition2, String condition3) {
        List<Integer> integers = userService.queryActiveUser(condition1, condition2, condition3);
        return integers;

    }

    @RequestMapping("/queryDistributionUser")
    public Map<String, List<Province>> queryDistributionUser(String sex, String sex2) {
        Map<String, List<Province>> map = userService.queryDistributionUser(sex, sex2);
        return map;
    }

    @RequestMapping("/queryAllUser")
    public Dto<User> queryAllUser(Integer page, Integer rows) {
        Dto<User> dto = userService.queryAllUser(page, rows);
        return dto;
    }

    @RequestMapping("/updateUser")
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @RequestMapping("/exportUser")
    public void exportUser(HttpServletResponse response, HttpSession session) {
        userService.exportUser(response, session);
    }

    @RequestMapping("changeUser")
    public Object changeUser(User user) {
        Object obj = userService.changeUser(user);
        return obj;
    }

    @RequestMapping("/login")
    public Object login(String phone, String password, String code) {
        return null;
    }

    @RequestMapping("/regist")
    public Object regist(String phone, String password) {
        return null;
    }

}
