package com.baizhi.ysk.controller;

import com.aliyuncs.exceptions.ClientException;
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
    public Object check(String phone, String code) {
        Object obj = identifyService.check(phone, code);
        return obj;
    }
}
