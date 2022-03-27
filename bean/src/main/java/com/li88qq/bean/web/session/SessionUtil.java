package com.li88qq.bean.web.session;

import com.li88qq.bean.web.redis.RedisKey;
import com.li88qq.bean.web.redis.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类
 *
 * @author li88qq
 * @version 1.0 2022/3/24 22:47
 */
@Component
public class SessionUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static final String P_TOKEN = "ptoken";
    private static final String AM_TOKEN = "amtoken";

    /**
     * 获取请求ip
     *
     * @return ip
     */
    public static String getIp() {
        HttpServletRequest request = getRequest();
        String header = request.getHeader("X-Real-IP");
        if (header != null && !header.equals("")) {
            return header;
        }
        return getRequest().getRemoteAddr();
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new RuntimeException("ServletRequestAttributes为null!");
        }
        return requestAttributes.getRequest();
    }

    /**
     * 获取用户信息
     *
     * @return 获取用户信息
     */
    public static UserToken getSession() {
        String token = getRequest().getHeader(P_TOKEN);
        if (token == null || token.equals("")) {
            return null;
        }
        return getRedis().get(RedisKey.P_USER_TOKEN, token, UserToken.class);
    }

    /**
     * 加入session
     */
    public static void setSession(String token, UserToken userToken) {
        getRedis().set(RedisKey.P_USER_TOKEN, token, userToken);
    }

    /**
     * 删除session
     */
    public static void deleteSession() {
        UserToken userToken = getSession();
        if (userToken != null) {
            getRedis().delete(RedisKey.P_USER_TOKEN, userToken.getToken());
        }
    }

    /**
     * 更新session过期时间
     */
    public static void updateSession() {
        UserToken userToken = getSession();
        if (userToken != null) {
            getRedis().expire(RedisKey.P_USER_TOKEN, userToken.getToken(), 5, 30);
        }
    }

    /**
     * 获取用户信息
     *
     * @return 获取用户信息
     */
    public static UserToken getAmSession() {
        String token = getRequest().getHeader(AM_TOKEN);
        if (token == null || token.equals("")) {
            return null;
        }
        return getRedis().get(RedisKey.AM_USER_TOKEN, token, UserToken.class);
    }

    /**
     * 加入session
     */
    public static void setAmSession(String token, UserToken userToken) {
        getRedis().set(RedisKey.AM_USER_TOKEN, token, userToken);
    }

    /**
     * 删除session
     */
    public static void deleteAmSession() {
        UserToken userToken = getSession();
        if (userToken != null) {
            getRedis().delete(RedisKey.AM_USER_TOKEN, userToken.getToken());
        }
    }

    /**
     * 更新session过期时间
     */
    public static void updateAmSession() {
        UserToken userToken = getSession();
        if (userToken != null) {
            getRedis().expire(RedisKey.AM_USER_TOKEN, userToken.getToken(), 5, 30);
        }
    }

    /**
     * 获取redis工具类
     *
     * @return redisUtil
     */
    public static RedisUtil getRedis() {
        RedisUtil redisUtil = applicationContext.getBean(RedisUtil.class);
        return redisUtil;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SessionUtil.applicationContext = applicationContext;
    }
}
