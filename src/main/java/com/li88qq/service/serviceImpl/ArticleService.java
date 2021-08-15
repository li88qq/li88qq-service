package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.entity.*;
import com.li88qq.service.repo.*;
import com.li88qq.service.request.article.*;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticleBySnVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import com.li88qq.service.service.IArticleService;
import com.li88qq.service.utils.*;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.page.PageableImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

        String labels = handleLabels(bo.getLabels());

        Long uid = SessionUtil.getUid();

        Article article = new Article();
        article.setUid(uid);
        article.setTitle(title);
        article.setOpen(bo.getOpen());
        article.setOriginal(bo.getOriginal());
        article.setQuote(quote);
        article.setWords(content.length());
        article.setSn(UUIDUtil.uuid19());
        article.setLabels(labels);

        Long id = articleRepo.saveToId(article).longValue();

        ArticleContent articleContent = new ArticleContent(id, bo.getContent());
        contentRepo.save(articleContent);

        if (!labels.equals("")) {
            updateLabels(id, labels, false, uid);
        }

        return ResponseUtil.ok();
    }

    @Override
    public Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo) {
        String title = bo.getTitle();
        String labels = bo.getLabels();
        title = StringUtil.like(title);
        labels = StringUtil.like(labels);

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
        Page<Map<String, Object>> pageData = articleRepo.findPage(uid, title, labels, bo.getState(), bo.getOpen(), bo.getOriginal(), _beginDate,
                _endDate, bo.getBeginCount(), bo.getEndCount(), pageable);

        return pageData.convert(GetArticlePageVo.class);
    }

    // 处理标签
    private String handleLabels(String labels) {
        labels = StringUtil.trim(labels);
        if (labels.equals("")) {
            return "";
        }

        String sep = ",";

        //空格,中文逗号全转英文逗号,多个英文逗号转为一个
        String regx = "\\s+|，+";
        labels = labels.replaceAll(regx, sep).replaceAll(",+", sep);
        if (labels.equals("")) {
            return "";
        }
        Set<String> labelSet = new HashSet<>(Arrays.asList(labels.split(sep)));

        ArticleLabel label = null;
        int length = 0;
        List<String> list = new ArrayList<>();
        for (String name : labelSet) {
            length = name.length();
            //限定2-10字符
            if (length >= 2 && length <= 10) {
                list.add(name);
            }
        }
        return String.join(",", list);
    }

    //处理标签关系
    private void updateLabels(Long articleId, String labels, boolean updateFlag, Long uid) {
        List<ArticleLabel> labelList = new ArrayList<>();
        String[] arr = labels.split(",");
        List<String> list = Arrays.asList(arr);
        list.forEach(a -> {
            ArticleLabel articleLabel = new ArticleLabel(a, uid);
            labelList.add(articleLabel);
        });

        //如果是修改标志,则先清空原来的关系
        if (updateFlag) {
            article_labelRepo.deleteById(articleId);
        }

        labelRepo.save(true, labelList);
        List<Long> ids = labelRepo.findIdByNames(list);

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
        String username = bo.getUsername();
        String labels = bo.getLabels();

        title = StringUtil.like(title);
        username = StringUtil.like(username);
        labels = StringUtil.like(labels);


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
        Page<Map<String, Object>> pageData = articleRepo.findAllPage(title, labels, username, bo.getOriginal(), _beginDate,
                _endDate, bo.getBeginCount(), bo.getEndCount(), pageable);

        return pageData.convert(GetAllPageVo.class);
    }

    @Override
    public GetArticleVo getArticle(Long id) {
        Long uid = SessionUtil.getUid();
        Article article = articleRepo.findById(uid, id);
        if (article == null) {
            throw new RuntimeException("数据不存在");
        }

        GetArticleVo vo = new GetArticleVo();
        vo.setTitle(article.getTitle());
        vo.setOpen(article.getOpen());
        vo.setOriginal(article.getOriginal());
        vo.setQuote(article.getQuote());
        vo.setLabels(article.getLabels());
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
        if (article == null) {
            return ResponseUtil.ok();
        }
        Long articleId = article.getId();
        articleRepo.updateReadCount(articleId);

        Long uid = SessionUtil.getUid();
        if (uid != null) {
            String ip = SessionUtil.getIp();
            ArticleReadLog readLog = new ArticleReadLog(articleId, uid, ip);
            readLogRepo.save(readLog);
        }

        return ResponseUtil.ok();
    }

    /**
     * 删除(逻辑删除)
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse delete(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();
        int count = articleRepo.deleteById(uid, id);
        return ResponseUtil.ok();
    }

    /**
     * 修改文章
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse update(UpdateArticleBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();
        Article article = articleRepo.findById(uid, id);
        if (article == null) {
            return ResponseUtil.error("数据不存在");
        }

        String title = StringUtil.trim(bo.getTitle());
        String quote = StringUtil.trim(bo.getQuote());

        String labels = handleLabels(bo.getLabels());

        article.setTitle(title);
        article.setOpen(bo.getOpen());
        article.setOriginal(bo.getOriginal());
        article.setQuote(quote);
        article.setLabels(labels);

        article.setUpdateDate(DateUtil.getTimestamp());

        articleRepo.update(article);

        updateLabels(id, labels, true, uid);

        return ResponseUtil.ok();
    }

    /**
     * 修改文章内容
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse updateContent(UpdateContentBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();
        Article article = articleRepo.findById(uid, id);
        if (article == null) {
            return ResponseUtil.error("数据不存在");
        }
        String content = bo.getContent();
        if (content == null) {
            content = "";
        }
        ArticleContent articleContent = new ArticleContent(id, content);
        articleRepo.update(articleContent);

        articleRepo.updateCt(id, DateUtil.getTimestamp(), content.length());
        return ResponseUtil.ok();
    }

    /**
     * 根据序列号查询文章
     *
     * @param sn
     * @return
     */
    @Override
    public GetArticleBySnVo getArticleBySn(String sn) {
        Article article = articleRepo.findBySn(sn);
        if (article == null) {
            throw new RuntimeException("数据不存在");
        }

        Long uid = article.getUid();
        User user = userRepo.find(User.class, uid);
        String username = user.getNickname();

        Long id = article.getId();
        ArticleContent articleContent = contentRepo.find(ArticleContent.class, id);
        String content = "";
        if (articleContent != null) {
            content = articleContent.getContent();
        }

        GetArticleBySnVo vo = new GetArticleBySnVo();
        vo.setTitle(article.getTitle());
        vo.setUsername(username);
        vo.setOriginal(article.getOriginal());
        vo.setQuote(article.getQuote());
        vo.setLabels(article.getLabels());
        vo.setContent(content);
        vo.setCreateDate(article.getCreateDate());
        vo.setReadCount(article.getReadCount());
        vo.setWords(article.getWords());
        return vo;
    }

}
