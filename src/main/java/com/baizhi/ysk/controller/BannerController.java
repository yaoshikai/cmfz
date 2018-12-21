package com.baizhi.ysk.controller;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAllBanner")
    public Dto<Banner> queryAllBanner(Integer page, Integer rows) {
        Dto<Banner> dto = bannerService.queryAllBanner(page, rows);
        return dto;
    }

    @RequestMapping("/addBanner")
    public void addBanner(MultipartFile file, Banner banner, HttpSession session) throws IOException {
        bannerService.addBanner(file, banner, session);
    }

    @RequestMapping("/deleteBanner")
    public String deleteBanner(Integer id, HttpSession session) {
        bannerService.deleteBanner(id, session);
        return "";
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner) {
        bannerService.updateBanner(banner);
    }

}
