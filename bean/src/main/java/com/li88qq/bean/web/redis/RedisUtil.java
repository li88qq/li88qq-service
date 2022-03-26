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
    public <T> T get(RedisKey redisKey, Object key, Class<T> tClass) {
        String value = stringRedisTemplate.opsForValue().get(redisKey.getKey() + key);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value, tClass);
    }

    /**
     * 设置值
     */
    public <T> void set(RedisKey redisKey, Object key, T value) {
        stringRedisTemplate.opsForValue()
                .set(redisKey.getKey() + key, JSON.toJSONString(value), redisKey.getTime(), redisKey.getTimeUnit());
    }

    /**
     * 删除
     */
    public void delete(RedisKey redisKey, Object key) {
        stringRedisTemplate.delete(redisKey.getKey() + key);
    }
}
