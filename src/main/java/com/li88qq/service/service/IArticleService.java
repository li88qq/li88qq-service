package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.article.GetAllPageBo;
import com.li88qq.service.request.article.GetArticlePageBo;
import com.li88qq.service.request.article.ReadBo;
import com.li88qq.service.request.article.SaveArticleBo;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import org.fastquery.page.Page;

public interface IArticleService {

    BaseResponse saveArticle(SaveArticleBo bo);

    Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo);

    Page<GetAllPageVo> getAllPage(GetAllPageBo bo);

    GetArticleVo getArticle(String id);

    BaseResponse read(ReadBo bo);
}
