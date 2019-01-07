package com.baizhi.ysk.indexserviceimpl;

import com.baizhi.ysk.entity.Article;
import com.baizhi.ysk.indexdao.IndexArticleDao;
import com.baizhi.ysk.indexservice.IndexArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexArticleServiceImpl implements IndexArticleService {
    @Autowired
    private IndexArticleDao indexArticleDao;

    @Override
    public List<Article> queryArticleIndex(String params) {
        List<Article> articles = indexArticleDao.queryArticleIndex(params);
        return articles;
    }
}
