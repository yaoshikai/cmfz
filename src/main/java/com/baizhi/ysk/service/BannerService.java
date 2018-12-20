package com.baizhi.ysk.service;

import com.baizhi.ysk.dto.BannerDto;
import com.baizhi.ysk.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface BannerService {
    BannerDto queryAllBanner(Integer page, Integer rows);

    void addBanner(MultipartFile file, Banner banner, HttpServletRequest request) throws IOException;

    void deleteBanner(Integer bannerId);

    void updateBanner(Banner banner);
}
