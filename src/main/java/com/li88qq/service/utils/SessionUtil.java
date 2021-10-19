package com.li88qq.service.utils;

import com.li88qq.service.dto.SessionUser;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session工具类
 */
public class SessionUtil {

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 取session
     *
     * @return
     */
    public static HttpSession getSession(boolean flag) {
        HttpServletRequest request = getRequest();
        Assert.notNull(request, "request不能为空");
        HttpSession session = request.getSession(flag);
        if (session != null) {
        }
        return session;
    }

    public static SessionUser getUser() {
        return (SessionUser) getAttribute("user");
    }

    public static Long getUid() {
        Long uid = null;
        SessionUser user = getUser();
        if (user != null) {
            uid = user.getUid();
        }
        return uid;
    }

    /**
     * 移除session
     */
    public static void removeSession() {
        getSession(false).invalidate();
    }

    /**
     * 取相应的session
     *
     * @return
     */
    public static Object getAttribute(String key) {
        return getSession(false).getAttribute(key);
    }

    /**
     * 删除相应的session
     */
    public static void removeAttribute(String key) {
        getSession(false).removeAttribute(key);
    }

    /**
     * 设置相应的session
     */
    public static void setAttribute(String key, Object obj) {
        getSession(true).setAttribute(key, obj);
    }

}
