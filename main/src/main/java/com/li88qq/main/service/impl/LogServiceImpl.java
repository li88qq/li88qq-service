package com.li88qq.main.service.impl;

import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.page.TPage;
import com.li88qq.main.dao.log.ActionLogMapper;
import com.li88qq.main.dao.log.LoginLogMapper;
import com.li88qq.main.dto.log.GetActionLogPageForm;
import com.li88qq.main.dto.log.GetActionLogPageVo;
import com.li88qq.main.dto.log.GetLoginLogPageForm;
import com.li88qq.main.dto.log.GetLoginLoginPageVo;
import com.li88qq.main.service.LogService;
import com.li88qq.main.util.SessionUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 日记
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:05
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private ActionLogMapper actionLogMapper;

    /**
     * 分页查询登录记录
     */
    @Override
    public TPage<GetLoginLoginPageVo> getLoginPage(GetLoginLogPageForm form) {
        Integer uid = SessionUtil.getUid();
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<GetLoginLoginPageVo> voPage = loginLogMapper.getPage(uid, form, pageable);
        return voPage.build();
    }

    /**
     * 分页查询操作记录
     */
    @Override
    public TPage<GetActionLogPageVo> getActionPage(GetActionLogPageForm form) {
        Integer uid = SessionUtil.getUid();
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<GetActionLogPageVo> voPage = actionLogMapper.getPage(uid, form, pageable);
        return voPage.build();
    }
}
