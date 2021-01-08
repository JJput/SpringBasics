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
 * 短信验证码缓存
 */
public class PhoneCodeCache {

    public static final String PREFIX_CODE = "_p_";
    public static final String LOGIN = "lo";
    public static final String REGISTER = "re";
    public static final String FORGET = "fo";
    private static Logger logger = LoggerFactory.getLogger(PhoneCodeCache.class);

    //LRU算法
    private static LoadingCache<String, String> localCache = CacheBuilder
            .newBuilder()
            .initialCapacity(PhoneCodeConfig.VerificationCode.INIT_NUM)
            .maximumSize(PhoneCodeConfig.VerificationCode.MAX_SIZE)
            .expireAfterAccess(PhoneCodeConfig.VerificationCode.EXPIRED_TIME, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });


    public static void setKey(String key, String value) {
        localCache.put(PhoneCodeCache.PREFIX_CODE + key, value);
    }

    public static void invalidate(String key) {
        localCache.invalidate(PhoneCodeCache.PREFIX_CODE + key);
    }

    public static String getValue(String key) {
        try {
            String value = localCache.get(PhoneCodeCache.PREFIX_CODE + key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            logger.error("localCache getValue error", e);
        }
        return null;

    }

    public static void main(String[] arg) {
        for (int i = 0; i < 100; i++) {
            String phone = "10101"+i;
            String code = i + "";
            PhoneCodeCache.setKey(phone, code);
        }
        int i = 0;

        while (i++ <= 1000) {
            try {
                Thread.sleep(10000);
                System.out.println("count = "+i);
                System.out.println("101012 = " +  PhoneCodeCache.getValue("101012"));
                System.out.println("101011 = " + PhoneCodeCache.getValue("101011"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
