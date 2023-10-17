package com.li88qq.main.service;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.main.dto.log.GetActionLogPageForm;
import com.li88qq.main.dto.log.GetActionLogPageVo;
import com.li88qq.main.dto.log.GetLoginLogPageForm;
import com.li88qq.main.dto.log.GetLoginLoginPageVo;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:03
 */
public interface LogService {

    /**
     * 分页查询登录记录
     */
    TPage<GetLoginLoginPageVo> getLoginPage(GetLoginLogPageForm form);

    /**
     * 分页查询操作记录
     */
    TPage<GetActionLogPageVo> getActionPage(GetActionLogPageForm form);
}
