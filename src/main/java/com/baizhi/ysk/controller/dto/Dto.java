package com.baizhi.ysk.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dto<T> implements Serializable {
    private Integer total;
    private List<T> rows;
}
