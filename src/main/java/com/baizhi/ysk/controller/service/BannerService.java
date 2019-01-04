package com.baizhi.ysk.controller.service;

import com.baizhi.ysk.controller.dto.Dto;
import com.baizhi.ysk.controller.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface BannerService {
    Dto<Banner> queryAllBanner(Integer page, Integer rows);

    void addBanner(MultipartFile file, Banner banner, HttpSession session);

    void deleteBanner(Integer bannerId, HttpSession session);

    void updateBanner(Banner banner);

    void exportBanner(HttpServletResponse response, HttpSession session);

    List<Banner> importBanner(HttpSession session);
}
