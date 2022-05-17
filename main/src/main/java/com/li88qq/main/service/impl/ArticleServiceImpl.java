package com.li88qq.main.service.impl;

import com.li88qq.bean.entity.article.Article;
import com.li88qq.bean.entity.article.ArticleDoc;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.main.dto.article.SaveArticleForm;
import com.li88qq.main.service.ArticleService;
import com.li88qq.utils.DateUtil;
import com.li88qq.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 文章管理
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:23
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private BaseMapper baseMapper;

    /**
     * 保存
     */
    @Override
    public BaseResponse save(SaveArticleForm form) {
        UserToken userToken = SessionUtil.getSession();
        Long uid = userToken.getUid();
        Article article = new Article();
        article.setUid(uid);
        article.setTitle(form.getTitle());
        article.setImg(form.getImg());
        article.setOpen(form.getOpen());
        article.setOriginal(form.getOriginal());
        article.setTransport(form.getTransport());
        article.setRno(UUIDUtil.uuid8());//8位

        Long id = baseMapper.saveId(article, Long.class);
        //文章编号,格式:年月日+id截取后4位
        String articleNo = String.format("%s%04d", DateUtil.format("yyyyMMdd"), id);

        Article updateArticle = BaseMapper.reset(Article.class);
        updateArticle.setId(id);
        updateArticle.setArticleNo(articleNo);
        baseMapper.updateNoNull(updateArticle);

        String doc = form.getDoc();
        String originalDoc = form.getOriginalDoc();

        ArticleDoc articleDoc = new ArticleDoc();
        articleDoc.setDoc(doc);
        articleDoc.setOriginalDoc(originalDoc);
        articleDoc.setId(id);

        baseMapper.save(articleDoc);

        return ResponseUtil.ok();
    }
}
