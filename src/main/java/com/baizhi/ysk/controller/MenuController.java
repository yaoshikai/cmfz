package com.baizhi.ysk.controller;

import com.baizhi.ysk.entity.Menu;
import com.baizhi.ysk.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/queryAll")
    public List<Menu> queryAll() {
        List<Menu> list = menuService.queryAll();
        return list;
    }

}
