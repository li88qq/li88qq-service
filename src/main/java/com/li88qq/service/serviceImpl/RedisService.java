package com.li88qq.service.serviceImpl;

import com.li88qq.service.constant.RedisConst;
import com.li88qq.service.dto.SessionUser;
import com.li88qq.service.utils.SessionUtil;
import com.li88qq.service.utils.TypeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis服务
 *
 * @author li88qq
 * @version 1.0 2021/8/24 23:44
 */
@Component
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置sessionUser
     *
     * @param sessionUser
     */
    public void setSessionUser(SessionUser sessionUser) {
        HttpSession session = SessionUtil.getSession(true);
        String key = RedisConst.initKey(RedisConst.KEY_SESSION, session.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("uid", sessionUser.getUid());
        map.put("loginId", sessionUser.getLoginId());
        map.put("visitor", sessionUser.isVisitor());

        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, RedisConst.EXPIRE_SESSION, TimeUnit.HOURS);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public SessionUser getSessionUser(String sessionId) {
        if (sessionId == null || sessionId.equals("")) {
            HttpSession session = SessionUtil.getSession(false);
            if (session == null) {
                return null;
            }
            sessionId = session.getId();
        }
        String key = RedisConst.initKey(RedisConst.KEY_SESSION, sessionId);
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            return null;
        }
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUid(TypeUtil.getLong(map.get("uid")));
        sessionUser.setLoginId(TypeUtil.getLong(map.get("loginId")));
        sessionUser.setVisitor(TypeUtil.getBoolean(map.get("visitor")));
        return sessionUser;
    }

    /**
     * 清除session
     */
    public void removeSession() {
        HttpSession session = SessionUtil.getSession(true);
        String key = RedisConst.initKey(RedisConst.KEY_SESSION, session.getId());
        redisTemplate.delete(key);
    }

    /**
     * 获取验证吗
     *
     * @return
     */
    public String getCaptcha(String cookie) {
        String key = RedisConst.initKey(RedisConst.KEY_CAPTCHA, cookie);
        Object codeObj = redisTemplate.opsForValue().get(key);
        if (codeObj == null) {
            return null;
        }
        return codeObj.toString();
    }

    /**
     * 放置验证吗
     *
     * @param cookie
     * @param code
     * @return
     */
    public void setCaptcha(String cookie, String code) {
        String key = RedisConst.initKey(RedisConst.KEY_CAPTCHA, cookie);
        redisTemplate.opsForValue().set(key, code, RedisConst.EXPIRE_CAPTCHA, TimeUnit.MINUTES);
    }

    /**
     * 放置验证吗
     *
     * @return
     */
    public void removeCaptcha(String cookie) {
        String key = RedisConst.initKey(RedisConst.KEY_CAPTCHA, cookie);
        redisTemplate.delete(key);
    }
}
