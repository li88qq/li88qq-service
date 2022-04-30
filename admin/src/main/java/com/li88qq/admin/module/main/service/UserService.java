package com.li88qq.admin.module.main.service;

import com.li88qq.admin.module.main.dto.user.UserPageForm;
import com.li88qq.admin.module.main.dto.user.UserPageVo;
import com.li88qq.db.dto.TPage;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2022/4/4 23:15
 */
public interface UserService {

    /**
     * 分页查询用户信息
     */
    TPage<UserPageVo> getPage(UserPageForm form);
}
