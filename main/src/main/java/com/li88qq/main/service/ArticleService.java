package com.li88qq.main.service;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.main.dto.article.SaveArticleForm;

/**
 * 文章
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:16
 */
public interface ArticleService {

    /**
     * 保存
     */
    BaseResponse save(SaveArticleForm form);
}
