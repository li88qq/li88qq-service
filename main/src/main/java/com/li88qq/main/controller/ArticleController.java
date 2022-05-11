package com.li88qq.main.controller;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.main.dto.article.SaveArticleForm;
import com.li88qq.main.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章管理
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:13
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public BaseResponse save(@RequestBody SaveArticleForm form) {
        return articleService.save(form);
    }
}
