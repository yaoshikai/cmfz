package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.dto.BannerDto;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.mapper.BannerMapper;
import com.baizhi.ysk.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    private BannerDto bannerDto;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BannerDto queryAllBanner(Integer page, Integer rows) {
        List<Banner> list = bannerMapper.queryAllBanner(page, rows);
        bannerDto.setRows(list);
        Integer count = bannerMapper.selectCount(new Banner());
        bannerDto.setTotal(count);
        return bannerDto;
    }

    @Override
    public void addBanner(MultipartFile file, Banner banner, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("upload");

        String originalFilename = file.getOriginalFilename();
        File f = new File(realPath + "/" + originalFilename);
        file.transferTo(f);

        banner.setImgPath("/upload/" + originalFilename);
        banner.setPubDate(new Date());
        banner.setStatus("N");

        bannerMapper.insert(banner);
    }

    @Override
    public void deleteBanner(Integer bannerId) {
        bannerMapper.deleteByPrimaryKey(bannerId);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateByPrimaryKeySelective(banner);
    }
}
