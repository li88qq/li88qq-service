package com.li88qq.admin.service;

import com.li88qq.admin.dto.log.LoginLogForm;
import com.li88qq.admin.dto.log.LoginLogVo;
import com.li88qq.db.dto.TPage;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2022/4/15 23:35
 */
public interface LogService {

    /**
     * 登录记录
     */
    TPage<LoginLogVo> logLog(LoginLogForm form);
}
