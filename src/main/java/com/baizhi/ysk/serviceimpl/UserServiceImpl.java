package com.baizhi.ysk.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;
import com.baizhi.ysk.mapper.UserMapper;
import com.baizhi.ysk.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Log4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Dto<User> userDto;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Integer> queryActiveUser(String condition1, String condition2, String condition3) {
        Integer integer1 = userMapper.queryActiveUser(condition1);
        Integer integer2 = userMapper.queryActiveUser(condition2);
        Integer integer3 = userMapper.queryActiveUser(condition3);
        List<Integer> list = new ArrayList<>();
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Province> queryDistributionUser(String sex) {
        List<Province> provinces = null;
        if ("男".equals(sex)) {
            provinces = userMapper.queryDistributionUserM();
        } else {
            provinces = userMapper.queryDistributionUserF();
        }
        return provinces;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Dto<User> queryAllUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> users = userMapper.selectAll();
        Integer count = userMapper.selectCount(null);
        userDto.setRows(users);
        userDto.setTotal(count);
        return userDto;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void exportUser(HttpServletResponse response, HttpSession session) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            String realPath = session.getServletContext().getRealPath("/head-pic");
            user.setHeadPic(realPath + "/" + user.getHeadPic());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息总览", ""), User.class, users);
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("用户信息.xls", "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
