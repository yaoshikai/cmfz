package com.baizhi.ysk.controller.controller;

import com.baizhi.ysk.controller.dto.Dto;
import com.baizhi.ysk.controller.entity.Banner;
import com.baizhi.ysk.controller.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public void addBanner(MultipartFile file, Banner banner, HttpSession session) {
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

    @RequestMapping("/exportBanner")
    public void exportBanner(HttpServletResponse response, HttpSession session) {
        bannerService.exportBanner(response, session);
    }

    @RequestMapping("/importBanner")
    public List<Banner> importBanner(HttpSession session) {
        List<Banner> banners = bannerService.importBanner(session);
        return banners;
    }

}
