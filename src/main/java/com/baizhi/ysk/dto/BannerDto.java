package com.baizhi.ysk.dto;

import com.baizhi.ysk.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto implements Serializable {
    private Integer total;
    private List<Banner> rows;
}
