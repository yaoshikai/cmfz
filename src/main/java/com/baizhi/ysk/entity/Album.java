package com.baizhi.ysk.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑名称", needMerge = true)
    private String title;
    @ExcelIgnore
    private Integer count;
    @Excel(name = "专辑封面", needMerge = true, type = 2)
    private String coverImg;
    @ExcelIgnore
    private Integer score;
    @Excel(name = "专辑作者", needMerge = true)
    private String author;
    @ExcelIgnore
    private String broadcast;
    @ExcelIgnore
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", format = "YYYY年MM月dd日", width = 20, needMerge = true)
    private Date pubDate;
    @ExcelCollection(name = "章节")
    private List<Chapter> children;
}
