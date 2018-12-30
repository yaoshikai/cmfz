package com.baizhi.ysk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identify")
public class IdentifyController {

    @RequestMapping("/obtain")
    public void obtain(String phone) {
    }
}
