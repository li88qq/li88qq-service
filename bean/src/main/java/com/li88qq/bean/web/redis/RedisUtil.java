package com.li88qq.bean.web.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis工具类
 *
 * @author li88qq
 * @version 1.0 2022/3/23 21:03
 */
@Component
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 取值
     */
    public <T> T get(RedisKey redisKey, String key, Class<T> tClass) {
        String value = stringRedisTemplate.opsForValue().get(redisKey.getKey() + key);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value, tClass);
    }

    /**
     * 设置值
     */
    public <T> void set(RedisKey redisKey, String key, T value) {
        stringRedisTemplate.opsForValue()
                .set(redisKey.getKey() + key, JSON.toJSONString(value), redisKey.getTime(), redisKey.getTimeUnit());
    }

    /**
     * 删除
     */
    public void delete(RedisKey redisKey, String key) {
        stringRedisTemplate.delete(redisKey.getKey() + key);
    }

    /**
     * 延长有效期
     */
    public void expire(RedisKey redisKey, String key, long minTimeout, long timeout) {
        String expireKey = redisKey.getKey() + key;
        Long expire = stringRedisTemplate.getExpire(expireKey, redisKey.getTimeUnit());
        if (expire != null && expire > 0) {
            if (expire <= minTimeout) {
                stringRedisTemplate.expire(expireKey, timeout, redisKey.getTimeUnit());
            }
        }
    }
}
