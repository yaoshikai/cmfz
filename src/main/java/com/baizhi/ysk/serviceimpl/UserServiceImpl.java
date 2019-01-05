package com.baizhi.ysk.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.ysk.conf.RandomSaltUtil;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.dto.ErrorDto;
import com.baizhi.ysk.dto.Province;
import com.baizhi.ysk.entity.User;
import com.baizhi.ysk.mapper.UserMapper;
import com.baizhi.ysk.service.UserService;
import com.github.pagehelper.PageHelper;
import io.goeasy.GoEasy;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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

    @Override
    public Object changeUser(User user) {
        if (user.getId() == null) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            if (user.getPhone() != null) {
                User user1 = new User();
                user1.setPhone(user.getPhone());
                User user2 = userMapper.selectOne(user1);
                if (user2 != null) {
                    return new ErrorDto(205, "手机号已存在");
                }
            }


            /*此处应该再判断头像是否更换，还没实现*/


            if (user.getPassword() != null) {
                String saltCode = RandomSaltUtil.generetRandomSaltCode();
                user.setSalt(saltCode);
                user.setPassword(DigestUtils.md5Hex(user.getPassword() + saltCode));
            }
            userMapper.updateByPrimaryKeySelective(user);
            User u = userMapper.selectByPrimaryKey(user.getId());
            return u;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object login(String phone, String password, String code, HttpSession session) {
        if (phone == null || (password == null && code == null)) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            User u = new User();
            u.setPhone(phone);
            User user = userMapper.selectOne(u);
            if (password != null) {
                if (user == null) {
                    return new ErrorDto(201, "用户不存在");
                } else {
                    String newPassword = DigestUtils.md5Hex(password + user.getSalt());
                    if (!newPassword.equals(user.getPassword())) {
                        return new ErrorDto(202, "密码错误");
                    } else {
                        session.setAttribute("user", user);
                        return user;
                    }
                }
            } else {
                /*
                从redis数据库中取短信验证码identifyCode,然后判断验证码code是否等于identifyCode
                 */
                ValueOperations ops = redisTemplate.opsForValue();
                String identifyCode = (String) ops.get("identifyCode");
                if (identifyCode.equals(code)) {
                    return user;
                } else {
                    return new ErrorDto(202, "验证码错误");
                }
            }
        }
    }

    @Override
    public Object regist(String phone, String password) {
        if (phone == null || password == null) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            String saltCode = RandomSaltUtil.generetRandomSaltCode();
            String newPassword = DigestUtils.md5Hex(password + saltCode);
            User u = new User();
            u.setSalt(saltCode);
            u.setPhone(phone);
            u.setPassword(newPassword);
            userMapper.insertSelective(u);

            User u2 = new User();
            u2.setPhone(phone);
            User user = userMapper.selectOne(u2);
            return user;
        }
    }
}
