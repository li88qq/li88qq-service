package com.li88qq.service.controller;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.article.GetArticlePageBo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.service.IArticleService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @PostMapping("/save")
    public BaseResponse save(SaveArticleBo bo) {
        return articleService.saveArticle(bo);
    }

    @GetMapping("/getArticlePage")
    public Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo) {
        return articleService.getArticlePage(bo);
    }

}
