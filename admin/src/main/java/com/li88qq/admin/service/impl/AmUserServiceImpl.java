package com.li88qq.admin.service.impl;

import com.li88qq.admin.dao.amuser.AmUserMapper;
import com.li88qq.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.service.AmUserService;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户(后台)管理
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:51
 */
@Service
public class AmUserServiceImpl implements AmUserService {

    @Resource
    private AmUserMapper amUserMapper;

    /**
     * 查询用户(后台)信息
     */
    @Override
    public AmUserInfo getInfo() {
        UserToken userToken = SessionUtil.getAmSession();
        Long uid = userToken.getUid();
        return amUserMapper.findInfo(uid);
    }
}
