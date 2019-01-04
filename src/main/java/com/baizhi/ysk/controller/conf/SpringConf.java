package com.baizhi.ysk.controller.conf;

import com.baizhi.ysk.controller.dto.Dto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConf {
    @Bean
    public Dto getBannerDto() {
        return new Dto();
    }
}
