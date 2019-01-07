package com.baizhi.ysk.controller;

import com.baizhi.ysk.entity.Guru;
import com.baizhi.ysk.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    @RequestMapping("/queryAllGuru")
    public List<Guru> queryAllGuru() {
        List<Guru> gurus = guruService.queryAllGuru();
        return gurus;
    }
}
