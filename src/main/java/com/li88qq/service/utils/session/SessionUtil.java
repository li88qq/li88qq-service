package com.li88qq.service.utils.session;

import com.alibaba.fastjson2.JSON;
import com.li88qq.service.utils.UUIDUtil;
import com.li88qq.service.utils.redis.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author li88qq
 * @version 1.0 2023/12/30 10:11
 */
public class SessionUtil {

    // header值
    public static final String HEADER_KEY = "Authorization";
    // token
    public static final String TOKEN_KEY = "token";
    public static final String USER_KEY = "user";
    // 有效期,单位小时
    public static final int TOKEN_HOUR = 8;

    /**
     * 登录时,放入token
     *
     * @param userToken
     * @return
     */
    public static String login(UserToken userToken) {
        int uid = userToken.getUid();

        // token
        String token = UUIDUtil.getUUID();
        String tokenKey = getTokenKey(token);
        RedisTemplate<String, Object> template = RedisUtil.getRedisTemplate();
        template.opsForValue().set(tokenKey, uid, TOKEN_HOUR, TimeUnit.HOURS);

        // 用户信息
        String userKey = getUserKey(uid);
        String userValue = JSON.toJSONString(userToken);
        template.opsForValue().set(userKey, userValue, TOKEN_HOUR, TimeUnit.HOURS);

        return token;
    }

    /**
     * token表达式
     *
     * @param token
     * @return
     */
    public static String getTokenKey(String token) {
        return String.format("%s:%s", TOKEN_KEY, token);
    }

    /**
     * user表达式
     *
     * @param uid
     * @return
     */
    public static String getUserKey(int uid) {
        return String.format("%s:%d", USER_KEY, uid);
    }

    /**
     * 退出，删除
     */
    public static void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(HEADER_KEY);
        if (token == null || token.isEmpty()) {
            return;
        }
        // 删除token
        String tokenKey = getTokenKey(token);
        RedisTemplate<String, Integer> template = RedisUtil.getRedisTemplate();

        Integer uid = template.opsForValue().getAndDelete(tokenKey);

        // 删除用户信息
        if (uid != null && uid > 0) {
            String userKey = getUserKey(uid);
            template.delete(userKey);
        }
    }

    /**
     * 获取当前uid
     */
    public static int getUid(ServerHttpRequest request) {
        List<String> headers = request.getHeaders().get(HEADER_KEY);
        if (headers == null || headers.isEmpty()) {
            return 0;
        }
        String token = headers.get(0);
        if (token == null || token.isEmpty()) {
            return 0;
        }
        String tokenKey = getTokenKey(token);
        RedisTemplate<String, Integer> template = RedisUtil.getRedisTemplate();
        Integer uid = template.opsForValue().get(tokenKey);
        if (uid == null || uid <= 0) {
            return 0;
        }
        return uid;
    }

    /**
     * 获取当前uid
     */
    public static int getUid() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(HEADER_KEY);
        if (token == null || token.isEmpty()) {
            return 0;
        }
        String tokenKey = getTokenKey(token);
        RedisTemplate<String, Integer> template = RedisUtil.getRedisTemplate();
        Integer uid = template.opsForValue().get(tokenKey);
        if (uid == null || uid <= 0) {
            return 0;
        }
        return uid;
    }

    /**
     * 获取当前用户信息
     */
    public static UserToken getUserToken() {
        UserToken userToken = new UserToken();
        int uid = getUid();
        if (uid <= 0) {
            return userToken;
        }

        String userKey = getUserKey(uid);
        RedisTemplate<String, String> template = RedisUtil.getRedisTemplate();
        String userValue = template.opsForValue().get(userKey);
        if (userValue != null) {
            userToken = JSON.parseObject(userValue, UserToken.class);
        }
        return userToken;
    }
}
