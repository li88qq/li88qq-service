package com.li88qq.main.service;

import com.li88qq.db.dto.TPage;
import com.li88qq.main.dto.log.LoginLogForm;
import com.li88qq.main.dto.log.LoginLogVo;

/**
 * 日记管理
 *
 * @author li88qq
 * @version 1.0 2022/9/8 23:34
 */
public interface LogService {

    /**
     * 登录记录
     */
    TPage<LoginLogVo> loginLog(LoginLogForm form);
}
