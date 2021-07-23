package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.article.GetArticlePageBo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.response.GetArticlePageVo;
import org.fastquery.page.Page;

public interface IArticleService {

    BaseResponse saveArticle(SaveArticleBo bo);

    Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo);
}
