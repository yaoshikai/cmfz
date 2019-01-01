package com.baizhi.ysk.controller;

import com.aliyuncs.exceptions.ClientException;
import com.baizhi.ysk.dto.IdentifyDto;
import com.baizhi.ysk.service.IdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identify")
public class IdentifyController {
    @Autowired
    private IdentifyService identifyService;

    @RequestMapping("/obtain")
    public void obtain(String phone) throws ClientException {
        identifyService.obtain(phone);
    }

    @RequestMapping("/check")
    public IdentifyDto check(String phone, String code) {
        IdentifyDto identifyDto = identifyService.check(phone, code);
        return identifyDto;
    }
}
