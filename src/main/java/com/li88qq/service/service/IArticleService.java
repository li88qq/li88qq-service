package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.article.SaveArticleBo;

public interface IArticleService {

    BaseResponse saveArticle(SaveArticleBo bo);
}
