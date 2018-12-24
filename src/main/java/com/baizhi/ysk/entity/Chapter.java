package com.baizhi.ysk.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "章节名称", width = 20)
    private String title;
    @ExcelIgnore
    private String size;
    @Excel(name = "时长")
    private String duration;
    @ExcelIgnore
    private String url;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", format = "YYYY年MM月dd日", width = 20)
    private Date uploadDate;
    @ExcelIgnore
    private Integer albumId;
}
