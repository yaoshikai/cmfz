package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.entity.Admin;
import com.baizhi.ysk.excep.CodeException;
import com.baizhi.ysk.mapper.AdminMapper;
import com.baizhi.ysk.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
        if (!code.equalsIgnoreCase(enCode)) throw new CodeException("验证码错误");

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(admin.getName(), admin.getPassword());

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new RuntimeException("账号不存在");
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("密码错误");
        }

    }
}
