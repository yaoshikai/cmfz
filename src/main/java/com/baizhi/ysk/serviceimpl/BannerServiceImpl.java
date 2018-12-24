package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.mapper.BannerMapper;
import com.baizhi.ysk.service.BannerService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private Dto<Banner> bannerDto;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Dto<Banner> queryAllBanner(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        List<Banner> list = bannerMapper.selectAll();
        bannerDto.setRows(list);
        Integer count = bannerMapper.selectCount(null);
        bannerDto.setTotal(count);
        return bannerDto;
    }

    @Override
    public void addBanner(MultipartFile file, Banner banner, HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("upload");

        String originalFilename = file.getOriginalFilename();
        File f = new File(realPath + "/" + originalFilename);
        try {
            file.transferTo(f);
        } catch (IOException e) {
        }

        banner.setImgPath(originalFilename);
        banner.setPubDate(new Date());

        bannerMapper.insert(banner);
    }

    @Override
    public void deleteBanner(Integer bannerId, HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("upload");
        Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
        File file = new File(realPath + "/" + banner.getImgPath());
        file.delete();
        bannerMapper.deleteByPrimaryKey(bannerId);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateByPrimaryKeySelective(banner);
    }
}
