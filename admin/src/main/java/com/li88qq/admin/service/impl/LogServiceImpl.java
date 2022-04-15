package com.li88qq.admin.service.impl;

import com.li88qq.admin.dao.log.LoginLogMapper;
import com.li88qq.admin.dto.log.LoginLogForm;
import com.li88qq.admin.dto.log.LoginLogVo;
import com.li88qq.admin.service.LogService;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.db.dto.TPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2022/4/15 23:36
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    /**
     * 分页查询登录记录
     */
    @Override
    public TPage<LoginLogVo> logLog(LoginLogForm form) {
        Page<LoginLogVo> pageData = loginLogMapper.findPage(form, new Pageable(form.getPage(), form.getPageSize()));
        return pageData.build();
    }
}
