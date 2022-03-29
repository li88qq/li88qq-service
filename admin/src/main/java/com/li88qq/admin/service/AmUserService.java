package com.li88qq.admin.service;

import com.li88qq.admin.dto.amuser.AmUserInfo;

/**
 * 用户(后台)
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:51
 */
public interface AmUserService {

    /**
     * 查询用户(后台)信息
     */
    AmUserInfo getInfo();
}
