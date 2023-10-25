package com.li88qq.main.util;

import com.li88qq.main.dto.token.UserToken;

/**
 * session工具类
 *
 * @author li88qq
 * @version 1.0 2023/10/25 21:59
 */
public class SessionUtil {

    /**
     * 获取当前用户token
     *
     * @return token
     */
    public static UserToken getToken() {
        UserToken token = new UserToken();
        return token;
    }

    /**
     * 获取当前用户id
     *
     * @return token
     */
    public static Integer getUid() {
        UserToken token = new UserToken();
        return token.getUid();
    }
}
