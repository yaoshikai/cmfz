package com.baizhi.ysk.indexdaoimpl;

import com.baizhi.ysk.conf.LuceneUtil;
import com.baizhi.ysk.entity.Article;
import com.baizhi.ysk.entity.Guru;
import com.baizhi.ysk.indexdao.IndexArticleDao;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class IndexArticleDaoImpl implements IndexArticleDao {
    @Override
    public List<Article> queryArticleIndex(String params) {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Article> list = new ArrayList<>();

        String[] fields = {"title", "content", "pubDate", "dharma"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, fields, new IKAnalyzer());
        Query query = null;
        try {
            query = multiFieldQueryParser.parse(params);
            TopDocs topDocs = indexSearcher.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                list.add(DocumentToArticle(document, query));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public Article DocumentToArticle(Document document, Query query) {
        /*高亮*/
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);

        Article article = new Article();
        Guru guru = new Guru();
        article.setGuru(guru);
        try {
            String bestId = highlighter.getBestFragment(new IKAnalyzer(), "id", document.get("id"));
            if (bestId == null) article.setId(Integer.valueOf(document.get("id")));
            else article.setId(Integer.valueOf(bestId));

            String bestTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
            if (bestTitle == null) article.setTitle(document.get("title"));
            else article.setTitle(bestTitle);

            String bestInsertImg = highlighter.getBestFragment(new IKAnalyzer(), "insertImg", document.get("insertImg"));
            if (bestInsertImg == null) article.setInsertImg(document.get("insertImg"));
            else article.setInsertImg(bestInsertImg);

            String bestContent = highlighter.getBestFragment(new IKAnalyzer(), "content", document.get("content"));
            if (bestContent == null) article.setContent(document.get("content"));
            else article.setContent(bestContent);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String bestDate = highlighter.getBestFragment(new IKAnalyzer(), "pubDate", document.get("pubDate"));
            Date d = simpleDateFormat.parse(document.get("pubDate"));
            if (bestDate == null) article.setPubDate(d);
            else article.setPubDate(simpleDateFormat.parse(bestDate));


            String bestDharma = highlighter.getBestFragment(new IKAnalyzer(), "dharma", document.get("dharma"));
            if (bestDharma == null) article.getGuru().setDharma(document.get("dharma"));
            else article.getGuru().setDharma(bestDharma);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return article;
    }
}
