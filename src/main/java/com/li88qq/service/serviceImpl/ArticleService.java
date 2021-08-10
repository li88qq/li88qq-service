package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.entity.*;
import com.li88qq.service.repo.*;
import com.li88qq.service.request.article.GetAllPageBo;
import com.li88qq.service.request.article.GetArticlePageBo;
import com.li88qq.service.request.article.ReadBo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import com.li88qq.service.service.IArticleService;
import com.li88qq.service.utils.*;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.page.PageableImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Resource
    private UserRepo userRepo;
    @Resource
    private ArticleReadLogRepo readLogRepo;

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

    @Override
    public Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo) {
        String title = bo.getTitle();
        title = StringUtil.like(title);

        Long _beginDate = null;
        Long _endDate = null;
        LocalDate beginDate = bo.getBeginDate();
        if (beginDate != null) {
            _beginDate = DateUtil.getTimestamp(beginDate.atTime(LocalTime.MIN));
        }
        LocalDate endDate = bo.getEndDate();
        if (endDate != null) {
            _endDate = DateUtil.getTimestamp(endDate.atTime(LocalTime.MAX));
        }

        Long uid = SessionUtil.getUid();
        Pageable pageable = new PageableImpl(bo.getPage(), bo.getSize());
        Page<Map<String, Object>> pageData = articleRepo.findPage(uid, title, bo.getState(), bo.getOpen(), bo.getOriginal(), _beginDate,
                _endDate, bo.getBeginCount(), bo.getEndCount(), pageable);

        return pageData.convert(GetArticlePageVo.class);
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

    @Override
    public Page<GetAllPageVo> getAllPage(GetAllPageBo bo) {
        String title = bo.getTitle();
        title = StringUtil.like(title);

        String username = bo.getUsername();
        username = StringUtil.like(username);

        Long _beginDate = null;
        Long _endDate = null;
        LocalDate beginDate = bo.getBeginDate();
        if (beginDate != null) {
            _beginDate = DateUtil.getTimestamp(beginDate.atTime(LocalTime.MIN));
        }
        LocalDate endDate = bo.getEndDate();
        if (endDate != null) {
            _endDate = DateUtil.getTimestamp(endDate.atTime(LocalTime.MAX));
        }

        Pageable pageable = new PageableImpl(bo.getPage(), bo.getSize());
        Page<Map<String, Object>> pageData = articleRepo.findAllPage(title, username, bo.getOriginal(), _beginDate,
                _endDate, bo.getBeginCount(), bo.getEndCount(), pageable);

        return pageData.convert(GetAllPageVo.class);
    }

    @Override
    public GetArticleVo getArticle(String id) {
        Article article = articleRepo.findBySn(id);
        if (article == null) {
            throw new RuntimeException("数据不存在");
        }
        Long uid = article.getUid();
        User user = userRepo.find(User.class, uid);
        String username = "";
        if (user != null) {
            username = user.getNickname();
        }

        String content = "";
        ArticleContent articleContent = contentRepo.find(ArticleContent.class, article.getId());
        if (articleContent != null) {
            content = articleContent.getContent();
        }

        GetArticleVo vo = new GetArticleVo();
        vo.setUsername(username);
        vo.setTitle(article.getTitle());
        vo.setOriginal(article.getOriginal());
        vo.setQuote(article.getQuote());
        vo.setCreateDate(article.getCreateDate());
        vo.setContent(content);
        vo.setWords(article.getWords());
        vo.setReadCount(article.getReadCount());
        return vo;
    }

    /**
     * 阅读文章
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse read(ReadBo bo) {
        String id = bo.getId();
        Article article = articleRepo.findBySn(id);
        if (article != null) {
            Long articleId = article.getId();
            articleRepo.updateReadCount(articleId);

            Long uid = SessionUtil.getUid();
            String ip = SessionUtil.getIp();
            ArticleReadLog readLog = new ArticleReadLog(articleId, uid, ip);
            readLogRepo.save(readLog);
        }
        return ResponseUtil.ok();
    }

}
