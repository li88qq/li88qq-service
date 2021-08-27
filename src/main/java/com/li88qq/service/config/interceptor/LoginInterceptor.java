package com.li88qq.service.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.SessionUser;
import com.li88qq.service.serviceImpl.RedisService;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = SessionUtil.getSession(false);
        if (session == null || SessionUtil.getUid() == null) {
            Cookie[] cookies = request.getCookies();
            String sessionId = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionId = cookie.getValue();
                        break;
                    }
                }
                if (sessionId != null && !sessionId.equals("")) {
                    SessionUser sessionUser = redisService.getSessionUser(sessionId);
                    if (sessionUser != null) {
                        session = SessionUtil.getSession(true);
                        session.setAttribute("user", sessionUser);
                        return true;
                    }
                }
            }

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
