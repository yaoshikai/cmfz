package com.baizhi.ysk.controller.serviceimpl;

import com.baizhi.ysk.controller.entity.Admin;
import com.baizhi.ysk.controller.mapper.AdminMapper;
import com.baizhi.ysk.controller.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void login(Admin admin, HttpSession session, String enCode) {
        String code = (String) session.getAttribute("code");
        if (!code.equalsIgnoreCase(enCode)) throw new RuntimeException("验证码错误");
        Admin ad = new Admin();
        ad.setName(admin.getName());
        Admin a = adminMapper.selectOne(ad);
        if (a == null) throw new RuntimeException("用户不存在");
        if (!a.getPassword().equals(admin.getPassword())) throw new RuntimeException("密码错误");
        session.setAttribute("admin", a);
    }
}
