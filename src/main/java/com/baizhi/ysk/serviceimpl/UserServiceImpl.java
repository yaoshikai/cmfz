package com.baizhi.ysk.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;
import com.baizhi.ysk.mapper.UserMapper;
import com.baizhi.ysk.service.UserService;
import com.github.pagehelper.PageHelper;
import io.goeasy.GoEasy;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, List<Province>> queryDistributionUser(String sex, String sex2) {
        Map<String, List<Province>> map = new HashMap<>();
        List<Province> provinces = userMapper.queryDistributionUserM();
        List<Province> provinces2 = userMapper.queryDistributionUserF();
        map.put("male", provinces);
        map.put("female", provinces2);
        return map;
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
        Integer integer1 = userMapper.queryActiveUser("近一周");
        Integer integer2 = userMapper.queryActiveUser("近两周");
        Integer integer3 = userMapper.queryActiveUser("近三周");
        List<Integer> list = new ArrayList<>();
        list.add(integer1);
        list.add(integer2);
        list.add(integer3);


        List<Province> provinces = userMapper.queryDistributionUserM();
        List<Province> provinces2 = userMapper.queryDistributionUserF();

        Map<String, Object> map = new HashMap<>();
        map.put("data", list);

        map.put("male", provinces);
        map.put("female", provinces2);

        String s = JSONObject.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-a65c5d1a24504b399fb5c85e23aa7b9e");
        goEasy.publish("cmfz", s);
        goEasy.publish("cmfz2", s);
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
