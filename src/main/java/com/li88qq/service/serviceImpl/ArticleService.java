package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.entity.Article;
import com.li88qq.service.entity.ArticleContent;
import com.li88qq.service.entity.ArticleLabel;
import com.li88qq.service.entity.Article_Label;
import com.li88qq.service.repo.ArticleContentRepo;
import com.li88qq.service.repo.ArticleLabelRepo;
import com.li88qq.service.repo.ArticleRepo;
import com.li88qq.service.repo.Article_LabelRepo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.service.IArticleService;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import com.li88qq.service.utils.StringUtil;
import com.li88qq.service.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 文章服务
 */
@Service
public class ArticleService implements IArticleService {

    @Resource
    private ArticleRepo articleRepo;
    @Resource
    private ArticleContentRepo contentRepo;
    @Resource
    private ArticleLabelRepo labelRepo;
    @Resource
    private Article_LabelRepo article_labelRepo;

    /**
     * 保存文章
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse saveArticle(SaveArticleBo bo) {
        String title = StringUtil.trim(bo.getTitle());
        String quote = StringUtil.trim(bo.getQuote());
        String content = bo.getContent();
        if (content == null) {
            content = "";
        }

        Long uid = SessionUtil.getUid();

        Article article = new Article();
        article.setUid(uid);
        article.setTitle(title);
        article.setOpen(bo.getOpen());
        article.setOriginal(bo.getOriginal());
        article.setQuote(quote);
        article.setWords(content.length());
        article.setSn(UUIDUtil.uuid19());

        Long id = articleRepo.saveToId(article).longValue();

        ArticleContent articleContent = new ArticleContent(id, bo.getContent());
        contentRepo.save(articleContent);

        handleLabels(id, uid, bo.getLabels());

        return ResponseUtil.ok();
    }

    // 处理标签
    private void handleLabels(Long articleId, Long uid, String labels) {
        labels = StringUtil.trim(labels);
        if (labels.equals("")) {
            return;
        }

        String sep = ",";

        //空格,中文逗号全转英文逗号,多个英文逗号转为一个
        String regx = "\\s+|，+";
        labels = labels.replaceAll(regx, sep).replaceAll(",+", sep);
        if (labels.equals("")) {
            return;
        }
        Set<String> labelSet = new HashSet<>(Arrays.asList(labels.split(sep)));
        List<ArticleLabel> labelList = new ArrayList<>();
        ArticleLabel label = null;
        int length = 0;
        for (String name : labelSet) {
            length = name.length();
            //限定2-10字符
            if (length >= 2 && length <= 10) {
                label = new ArticleLabel(name, uid);
                labelList.add(label);
            }
        }

        if (labelList.isEmpty()) {
            return;
        }

        labelRepo.save(true, labelList);
        List<Long> ids = labelRepo.findIdByNames(labelSet);

        List<Article_Label> article_labels = new ArrayList<>();
        Article_Label article_label = null;
        for (Long id : ids) {
            article_label = new Article_Label(articleId, id);
            article_labels.add(article_label);
        }

        article_labelRepo.save(false, article_labels);
    }

}
