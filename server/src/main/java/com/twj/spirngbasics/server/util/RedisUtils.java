package com.twj.spirngbasics.server.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * 存入redis对象,设置过期时间.
     *
     * @param key   k
     * @param value v
     * @param i     时长
     * @param time  时间单位
     */
    public static void set(String key, String value, int i, TimeUnit time) {
        redisTemplate.opsForValue().set(key, value, i, time);
    }

    /**
     * 取出redis对象
     *
     * @param key k
     * @return
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除redis对象
     *
     * @param key k
     * @return
     */
    public static boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 存入redis对象,不设置过期时间
     *
     * @param key   k
     * @param value v
     */
    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存入redis hash数据类型
     *
     * @param key
     * @param map
     */
    public static void setHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 往redis现有的hash中添加一条数据
     *
     * @param key     redis-key
     * @param hashKey hash-key
     * @param value   hash-value
     */
    public static void setHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 判断redis中哈希数据key是否存在
     *
     * @param key     redis-key
     * @param hashKey hash-key
     * @return
     */
    public static boolean isEmptyHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    /**
     * 获取redis中key对应的hash表的所有键值对
     *
     * @param key
     * @return
     */
    public static Map<String, Object> getHash(String key) {
        //get方法，根据key和hashkey找出对应的值
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取redis中哈希数据key对应的value
     *
     * @param key     redis-key
     * @param hashKey hash-key
     * @return
     */
    public static Object getHashValue(String key, String hashKey) {
        //get方法，根据key和hashkey找出对应的值
        return redisTemplate.opsForHash().get(key, hashKey);
    }


    /**
     * 删除redis中对应哈希数据的value
     *
     * @param key     redis-key
     * @param hashKey hash-key
     */
    public static void delHash(String key, String hashKey) {
        //delete方法，删除key对应的hash的hashkey及其value
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 保持redis连接
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void KeepAlive() {
        set("alive", "1");
//        log.info("redis keep alive...");
    }

}
