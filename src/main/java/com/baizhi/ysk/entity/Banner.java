package com.baizhi.ysk.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "banner")
public class Banner implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "banner_id")
    @ExcelIgnore
    private Integer id;
    @Excel(name = "轮播图标题")
    private String title;
    @Excel(name = "图片", type = 2, height = 20, width = 20)
    private String imgPath;
    @Excel(name = "状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", format = "YYYY年MM月dd日", width = 20)
    private Date pubDate;
    @Excel(name = "描述")
    private String description;
}
