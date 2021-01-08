package com.twj.spirngbasics.server.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twj.spirngbasics.server.config.PhoneCodeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 邮箱验证码缓存
 */
public class EmailCodeCache {

    public static final String PREFIX_CODE = "_e_";
    private static Logger logger = LoggerFactory.getLogger(EmailCodeCache.class);

    //LRU算法
    private static LoadingCache<String, String> localCache = CacheBuilder
            .newBuilder()
            .initialCapacity(PhoneCodeConfig.EmailCode.INIT_NUM)
            .maximumSize(PhoneCodeConfig.EmailCode.MAX_SIZE)
            .expireAfterAccess(PhoneCodeConfig.EmailCode.EXPIRED_TIME, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(PREFIX_CODE + key, value);
    }

    public static String getValue(String key) {
        try {
            String value = localCache.get(PREFIX_CODE + key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            logger.error("localCache getValue error", e);
        }
        return null;

    }

    public static void invalidate(String key) {
        localCache.invalidate(PREFIX_CODE + key);
    }
}
