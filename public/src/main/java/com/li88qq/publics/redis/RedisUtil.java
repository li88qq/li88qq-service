package com.li88qq.publics.redis;

import com.alibaba.fastjson2.JSON;
import com.li88qq.publics.common.BeanUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author li88qq
 * @version 1.0 2024/1/9 21:02
 */
public class RedisUtil {

    /**
     * 获取值
     *
     * @param key    key
     * @param tClass 类型
     * @return 值
     */
    public static <T> T getValue(String key, Class<T> tClass) {
        String value = getTemplate().opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value, tClass);
    }

    public static <T> void setValue(String key, T value) {
        getTemplate().opsForValue().set(key, JSON.toJSONString(value));
    }

    public static StringRedisTemplate getTemplate() {
        return BeanUtil.getBean(StringRedisTemplate.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> RedisTemplate<String, T> getRedisTemplate() {
        RedisTemplate<String, T> bean = (RedisTemplate<String, T>) BeanUtil.getBeanByName("redisTemplate",RedisTemplate.class);
        return bean;
    }
}
