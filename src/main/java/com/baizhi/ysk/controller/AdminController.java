package com.baizhi.ysk.controller;

import com.baizhi.ysk.entity.Admin;
import com.baizhi.ysk.service.AdminService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
@Log4j
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session, String enCode) {
        try {
            adminService.login(admin, session, enCode);
            return "right";
        } catch (Exception e) {
            log.debug(e.getMessage());
            return e.getMessage();
        }
    }

    @RequestMapping("logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
