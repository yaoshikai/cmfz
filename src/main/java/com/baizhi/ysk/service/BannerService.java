package com.baizhi.ysk.service;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface BannerService {
    Dto<Banner> queryAllBanner(Integer page, Integer rows);

    void addBanner(MultipartFile file, Banner banner, HttpSession session) throws IOException;

    void deleteBanner(Integer bannerId, HttpSession session);

    void updateBanner(Banner banner);
}
