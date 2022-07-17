package com.li88qq.main.service.impl;

import com.li88qq.bean.entity.system.User;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.main.dao.UserMapper;
import com.li88qq.main.dto.my.BaseInfoVo;
import com.li88qq.main.dto.my.DetailVo;
import com.li88qq.main.service.MyService;
import com.li88qq.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2022/7/17 23:02
 */
@Service
public class MyServiceImpl implements MyService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询基本信息
     */
    @Override
    public BaseInfoVo base() {
        UserToken session = SessionUtil.getSession();
        Long uid = session.getUid();
        User user = userMapper.findById(uid);
        BaseInfoVo vo = new BaseInfoVo();
        vo.setName(user.getName());
        vo.setHead(user.getHead());
        vo.setLastLoginDate(user.getLastLoginDate());
        vo.setLastLoginIp(user.getLastLoginIp());
        return vo;
    }

    /**
     * 查询详细信息
     */
    @Override
    public DetailVo detail() {
        UserToken session = SessionUtil.getSession();
        Long uid = session.getUid();
        User user = userMapper.findById(uid);

        String mobile = user.getMobile();
        mobile = RegexUtil.markMobile(mobile);

        DetailVo vo = new DetailVo();
        vo.setName(user.getName());
        vo.setHead(user.getHead());
        vo.setMobile(mobile);
        vo.setEmail(user.getEmail());
        vo.setCreateDate(user.getCreateDate());
        vo.setLastLoginDate(user.getLastLoginDate());
        vo.setLastLoginIp(user.getLastLoginIp());
        return vo;
    }
}
