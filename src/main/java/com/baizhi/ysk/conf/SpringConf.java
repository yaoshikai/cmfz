package com.baizhi.ysk.conf;

import com.baizhi.ysk.dto.BannerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConf {
    @Bean
    public BannerDto getBannerDto() {
        return new BannerDto();
    }

    @Bean
    public CreateValidateCode getCreateValidateCode() {
        return new CreateValidateCode();
    }
}
