package com.li88qq.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author li88qq
 * @version 1.0 2021/8/24 23:44
 */
@Component
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire);
    }

    /**
     * 取值
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        T result = null;
        if (value == null) {
            return null;
        }
        try {
            String jsonString = JSON.toJSONString(value);
            result = JSON.parseObject(jsonString, clazz);
        } catch (Exception e) {
        }
        return result;
    }
}
