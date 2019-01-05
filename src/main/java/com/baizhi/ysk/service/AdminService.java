package com.baizhi.ysk.service;

import com.baizhi.ysk.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    void login(Admin admin, HttpSession session, String enCode);
}
