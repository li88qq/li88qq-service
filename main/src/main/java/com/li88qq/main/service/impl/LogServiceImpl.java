package com.li88qq.main.service.impl;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.main.dto.log.GetActionLogPageForm;
import com.li88qq.main.dto.log.GetActionLogPageVo;
import com.li88qq.main.dto.log.GetLoginLogPageForm;
import com.li88qq.main.dto.log.GetLoginLoginPageVo;
import com.li88qq.main.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 日记
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:05
 */
@Service
public class LogServiceImpl implements LogService {

    /**
     * 分页查询登录记录
     */
    @Override
    public TPage<GetLoginLoginPageVo> getLoginPage(GetLoginLogPageForm form) {
        return null;
    }

    /**
     * 分页查询操作记录
     */
    @Override
    public TPage<GetActionLogPageVo> getActionPage(GetActionLogPageForm form) {
        return null;
    }
}
