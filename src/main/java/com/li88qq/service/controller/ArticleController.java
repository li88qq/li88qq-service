package com.li88qq.service.controller;

import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.article.GetAllPageBo;
import com.li88qq.service.request.article.GetArticlePageBo;
import com.li88qq.service.request.article.ReadBo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import com.li88qq.service.service.IArticleService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @AcLog(acType = ActionType.UPDATE, title = "保存文章", detail = "bo|title")
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
    public GetArticleVo getArticle(@RequestParam @NotBlank(message = "参数错误") String id) {
        return articleService.getArticle(id);
    }

    /**
     * 阅读文章
     *
     * @param bo
     * @return
     */
    @PostMapping("/read")
    public BaseResponse read(@RequestBody ReadBo bo) {
        return articleService.read(bo);
    }

}
