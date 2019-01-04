package com.baizhi.ysk.controller.controller;

import com.baizhi.ysk.controller.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstPage")
public class FirstPageController {
    @Autowired
    private FirstPageService firstPageService;

    @RequestMapping("/queryAll")
    public Object queryAll(Integer uid, String type, String subType) {
        Object obj = firstPageService.queryAll(uid, type, subType);
        return obj;
    }

    @RequestMapping("/queryOneAlbum")
    public Object queryOneAlbum(Integer id, Integer uid) {
        Object obj = firstPageService.queryOneAlbum(id, uid);
        return obj;
    }

    @RequestMapping("/queryMember")
    public Object queryMember(Integer uid) {
        Object obj = firstPageService.queryMember(uid);
        return obj;
    }


}