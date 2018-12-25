package com.baizhi.ysk.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cmfz_user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "手机号", width = 20)
    private String phone;
    @Excel(name = "密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "个性签名")
    private String sign;
    @Excel(name = "头像", type = 2, height = 20, width = 20)
    private String headPic;
    @Excel(name = "真实姓名")
    private String name;
    @Excel(name = "法名", width = 20)
    private String dharma;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", format = "YYYY年MM月dd日", width = 20)
    private Date regDate;
}
