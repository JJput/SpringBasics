package com.twj.spirngbasics.gateway.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @作者: JJ
 * @创建时间: 2020/7/29 上午11:19
 * @Version 1.0
 * @描述:
 */
@Component
public class RedisUtils {

    private static RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static void set(String key, String value, int i, TimeUnit time) {
        redisTemplate.opsForValue().set(key, value, i, time);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static boolean del(String key) {
        return redisTemplate.delete(key);
    }



}
