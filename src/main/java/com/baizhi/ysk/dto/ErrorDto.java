package com.baizhi.ysk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements Serializable {
    private Integer errorCode;
    private String errorMsg;
}
