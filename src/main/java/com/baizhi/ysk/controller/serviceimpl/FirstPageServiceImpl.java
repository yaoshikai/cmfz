package com.baizhi.ysk.controller.serviceimpl;

import com.baizhi.ysk.controller.dto.*;
import com.baizhi.ysk.controller.entity.*;
import com.baizhi.ysk.controller.mapper.*;
import com.baizhi.ysk.controller.service.FirstPageService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirstPageServiceImpl implements FirstPageService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object queryAll(Integer uid, String type, String subType) {
        if (uid == null || type == null) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            if ("all".equals(type)) {
                Banner banner = new Banner();
                banner.setStatus("Y");
                List<Banner> banners = bannerMapper.select(banner);
                List<Album> albums = albumMapper.selectAll();
                List<Article> articles = articleMapper.selectAll();
                FirstPageDto firstPageDto = new FirstPageDto(banners, albums, articles);
                return firstPageDto;
            } else if ("wen".equals(type)) {
                List<Album> albums = albumMapper.selectAll();
                AlbumDto albumDto = new AlbumDto(albums);
                return albumDto;
            } else if ("si".equals(type)) {
                if (subType == null) {
                    return new ErrorDto(200, "参数不能为空");
                } else {
                    if ("ssyj".equals(subType)) {
                        List<Article> articles = articleMapper.queryAllArticleBySsyj(uid);
                        ArticleDto articleDto = new ArticleDto(articles);
                        return articleDto;
                    } else if ("xmfy".equals(subType)) {
                        List<Article> articles = articleMapper.queryAllArticleByXmfy(uid);
                        ArticleDto articleDto = new ArticleDto(articles);
                        return articleDto;
                    } else {
                        return new ErrorDto(200, "si的子类型不能为空");
                    }
                }
            } else {
                return new ErrorDto(200, "类型不能为空");
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object queryOneAlbum(Integer id, Integer uid) {
        if (id == null || uid == null) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            Album album = albumMapper.queryOneAlbum(id);
            Chapter t = new Chapter();
            t.setAlbumId(id);
            List<Chapter> chapters = chapterMapper.select(t);
            return new AlbumChapterDto(album, chapters);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object queryMember(Integer uid) {
        if (uid == null) {
            return new ErrorDto(200, "参数不能为空");
        } else {
            PageHelper.startPage(1, 5);
            List<User> users = userMapper.selectAll();
            return users;
        }
    }
}
