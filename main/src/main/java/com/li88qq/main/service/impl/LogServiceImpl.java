package com.li88qq.main.service.impl;

import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.db.dto.TPage;
import com.li88qq.main.dao.ActionLogMapper;
import com.li88qq.main.dao.LoginLogMapper;
import com.li88qq.main.dto.log.ActionLogForm;
import com.li88qq.main.dto.log.ActionLogVo;
import com.li88qq.main.dto.log.LoginLogForm;
import com.li88qq.main.dto.log.LoginLogVo;
import com.li88qq.main.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日记管理
 *
 * @author li88qq
 * @version 1.0 2022/9/8 23:37
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private ActionLogMapper actionLogMapper;

    /**
     * 登录记录
     */
    @Override
    public TPage<LoginLogVo> loginLog(LoginLogForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Long uid = SessionUtil.getSession().getUid();
        Page<LoginLogVo> page = loginLogMapper.findPage(form, uid, pageable);
        return page.build();
    }

    /**
     * 操作记录
     */
    @Override
    public TPage<ActionLogVo> actionLog(ActionLogForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Long uid = SessionUtil.getSession().getUid();
        Page<ActionLogVo> page = actionLogMapper.findPage(form, uid, pageable);
        return page.build();
    }
}
