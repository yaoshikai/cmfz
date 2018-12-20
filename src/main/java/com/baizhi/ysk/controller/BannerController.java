package com.baizhi.ysk.controller;

import com.baizhi.ysk.dto.BannerDto;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAllBanner")
    public BannerDto queryAllBanner(Integer page, Integer rows) {
        BannerDto dto = bannerService.queryAllBanner(page, rows);
        return dto;
    }

    @RequestMapping("/addBanner")
    public void addBanner(MultipartFile file, Banner banner, HttpServletRequest request) throws IOException {
        bannerService.addBanner(file, banner, request);
    }

    @RequestMapping("/deleteBanner")
    public String deleteBanner(Integer id) {
        bannerService.deleteBanner(id);
        return "";
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner) {
        bannerService.updateBanner(banner);
    }

}
