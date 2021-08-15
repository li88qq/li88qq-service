package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.request.article.*;
import com.li88qq.service.response.GetAllPageVo;
import com.li88qq.service.response.GetArticleBySnVo;
import com.li88qq.service.response.GetArticlePageVo;
import com.li88qq.service.response.GetArticleVo;
import org.fastquery.page.Page;

public interface IArticleService {

    BaseResponse saveArticle(SaveArticleBo bo);

    Page<GetArticlePageVo> getArticlePage(GetArticlePageBo bo);

    Page<GetAllPageVo> getAllPage(GetAllPageBo bo);

    GetArticleVo getArticle(Long id);

    BaseResponse read(ReadBo bo);

    BaseResponse delete(IdBo bo);

    BaseResponse update(UpdateArticleBo bo);

    BaseResponse updateContent(UpdateContentBo bo);

    GetArticleBySnVo getArticleBySn(String sn);
}
