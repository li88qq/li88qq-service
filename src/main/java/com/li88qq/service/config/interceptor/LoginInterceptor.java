package com.li88qq.service.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = SessionUtil.getSession(false);
        if (session == null || SessionUtil.getUid() == null) {
            BaseResponse error = ResponseUtil.response(ResponseState.NO_LOGIN);
            errorResponse(response, error);
            return false;
        }

        String method = request.getMethod();
        if (method.equals("POST")) {
            boolean visitor = SessionUtil.getUser().isVisitor();
            String path = request.getRequestURI();
            if (visitor && !path.equals("/logout")) {
                BaseResponse error = ResponseUtil.error("体验账号不支持该操作");
                errorResponse(response, error);
                return false;
            }
        }

        return true;
    }

    private void errorResponse(HttpServletResponse response, BaseResponse error) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(error));
        response.flushBuffer();
    }
}
