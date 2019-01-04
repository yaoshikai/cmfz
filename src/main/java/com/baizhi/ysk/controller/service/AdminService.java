package com.baizhi.ysk.controller.service;

import com.baizhi.ysk.controller.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    void login(Admin admin, HttpSession session, String enCode);
}
