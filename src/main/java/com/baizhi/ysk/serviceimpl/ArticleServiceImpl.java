package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.conf.LuceneUtil;
import com.baizhi.ysk.dto.Dto;
import com.baizhi.ysk.entity.Article;
import com.baizhi.ysk.entity.Guru;
import com.baizhi.ysk.mapper.ArticleMapper;
import com.baizhi.ysk.mapper.GuruMapper;
import com.baizhi.ysk.service.ArticleService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private Dto<Article> dto;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private GuruMapper guruMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Dto<Article> queryAllArticle(Integer page, Integer rows) {
        List<Article> articles = articleMapper.queryAllArticle(page, rows);
        Integer count = articleMapper.selectCount(null);
        dto.setTotal(count);
        dto.setRows(articles);
        return dto;
    }

    @Override
    public void addArticle(Article article, MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            article.setInsertImg(storePath.getFullPath());
            article.setPubDate(new Date());

            articleMapper.insertSelective(article);
            Guru guru = guruMapper.selectByPrimaryKey(article.getGuruId());
            article.setGuru(guru);
            IndexWriter indexWriter = LuceneUtil.getIndexWriter();

            indexWriter.addDocument(ArticleToDocument(article));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Document ArticleToDocument(Article article) {
        Document document = new Document();
        document.add(new IntField("id", article.getId(), Field.Store.YES));
        document.add(new StringField("title", article.getTitle(), Field.Store.YES));
        document.add(new StringField("insertImg", article.getInsertImg(), Field.Store.YES));
        document.add(new TextField("content", article.getContent(), Field.Store.YES));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(article.getPubDate());
        document.add(new StringField("pubDate", format, Field.Store.YES));
        document.add(new StringField("dharma", article.getGuru().getDharma(), Field.Store.YES));
        return document;
    }
}
