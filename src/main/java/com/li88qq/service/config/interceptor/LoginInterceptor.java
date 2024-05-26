package com.li88qq.service.config.interceptor;

import com.li88qq.service.config.web.response.ResponseCode;
import com.li88qq.service.config.web.response.ResponseException;
import com.li88qq.service.utils.session.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截
 *
 * @author lm
 * @version 1.0 2024/5/26 16:10
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int uid = SessionUtil.getUid();
        if (uid <= 0) {
            throw new ResponseException(ResponseCode.NO_LOGIN);
        }
        return true;
    }
}
