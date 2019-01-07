package com.baizhi.ysk.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Banner;
import com.baizhi.ysk.mapper.BannerMapper;
import com.baizhi.ysk.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private Dto<Banner> bannerDto;
    @Autowired
    FastFileStorageClient fastFileStorageClient;

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
    public void addBanner(MultipartFile file, Banner banner) {
        StorePath storePath = null;
        try {
            storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        banner.setImgPath(storePath.getFullPath());
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

    @Override
    public void exportBanner(HttpServletResponse response, HttpSession session) {
        List<Banner> banners = bannerMapper.selectAll();
        for (Banner banner : banners) {
            String realPath = session.getServletContext().getRealPath("/upload");
            banner.setImgPath(realPath + "/" + banner.getImgPath());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图总览", ""), Banner.class, banners);
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("轮播图.xls", "utf-8"));
            response.setContentType("application/vnd.ms-excel");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Banner> importBanner(HttpSession session) {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);

        List<Banner> list = ExcelImportUtil.importExcel(new File("E:/poiimport/轮播图.xls"), Banner.class, importParams);
        return list;
    }
}
