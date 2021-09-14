package com.li88qq.service.controller;

import com.li88qq.service.constant.AcLogConst;
import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.request.article.*;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import com.li88qq.service.service.IArticleService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章管理
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    /**
     * 保存文章
     *
     * @param bo
     * @return
     */
    @PostMapping("/save")
    @AcLog(acType = ActionType.SAVE, title = "保存文章", detail = "bo|title,labels", prefix = AcLogConst.ARTICLE)
    public BaseResponse save(@RequestBody SaveArticleBo bo) {
        return articleService.saveArticle(bo);
    }

    /**
     * 查询我的文章
     *
     * @param bo
     * @return
     */
    @GetMapping("/getArticlePage")
    public Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo) {
        return articleService.getArticlePage(bo);
    }

    /**
     * 查询所有文章
     *
     * @param bo
     * @return
     */
    @GetMapping("/getAllPage")
    public Page<GetAllPageVo> getAllPage(GetAllPageBo bo) {
        return articleService.getAllPage(bo);
    }

    /**
     * 查询文章信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public GetArticleVo getArticle(@RequestParam Long id) {
        return articleService.getArticle(id);
    }

    /**
     * 删除文章
     *
     * @param bo
     * @return
     */
    @PostMapping("/delete")
    @AcLog(acType = ActionType.DELETE, title = "删除文章", detail = "bo|id", prefix = AcLogConst.ARTICLE)
    public BaseResponse delete(@RequestBody IdBo bo) {
        return articleService.delete(bo);
    }

    /**
     * 修改文章
     *
     * @param bo
     * @return
     */
    @PostMapping("/update")
    @AcLog(acType = ActionType.UPDATE, title = "修改文章", detail = "bo|id,title,labels", prefix = AcLogConst.ARTICLE)
    public BaseResponse update(@RequestBody UpdateArticleBo bo) {
        return articleService.update(bo);
    }

    /**
     * 修改文章内容
     *
     * @param bo
     * @return
     */
    @PostMapping("/updateContent")
    @AcLog(acType = ActionType.UPDATE, title = "修改文章内容", detail = "bo|id", prefix = AcLogConst.ARTICLE)
    public BaseResponse updateContent(@RequestBody UpdateContentBo bo) {
        return articleService.updateContent(bo);
    }

}
