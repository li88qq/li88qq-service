package com.li88qq.admin.service.impl;

import com.li88qq.admin.dao.user.UserMapper;
import com.li88qq.admin.dto.user.UserPageForm;
import com.li88qq.admin.dto.user.UserPageVo;
import com.li88qq.admin.service.UserService;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.db.dto.TPage;
import com.li88qq.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2022/4/4 23:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询用户列表
     */
    @Override
    public TPage<UserPageVo> getPage(UserPageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<UserPageVo> page = userMapper.findPage(form, pageable);
        page.forEach(vo -> {
            String mobile = vo.getMobile();
            vo.setMobile(RegexUtil.markMobile(mobile));
        });
        return page.build();
    }
}