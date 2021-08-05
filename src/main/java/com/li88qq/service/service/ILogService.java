package com.li88qq.service.service;

import com.li88qq.service.request.log.GetLoginPageBo;
import com.li88qq.service.response.GetLoginPageVo;
import org.fastquery.page.Page;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2021/8/5 22:05
 */
public interface ILogService {

    Page<GetLoginPageVo> getLoginPage(GetLoginPageBo bo);
}
