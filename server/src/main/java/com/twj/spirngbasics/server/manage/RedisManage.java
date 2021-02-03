package com.twj.spirngbasics.server.manage;


import com.alibaba.fastjson.JSONObject;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.util.RedisUtils;

import java.util.concurrent.TimeUnit;

/**
 * @作者: JJ
 * @创建时间: 2020/11/17 下午8:06
 * @Version 1.0
 * @描述:
 */
public class RedisManage {

    //用户信息缓存    30天后过期
    private static final int USER_INFO_TIME = 30;
    private static final TimeUnit USER_INFO_TIME_UNIT = TimeUnit.DAYS;

    /**
     * 设置用户token
     *
     * @param k 键
     * @param v 值
     */
    public static void setUserToken(String k, User v) {
        RedisUtils.set(k, JSONObject.toJSONString(v), USER_INFO_TIME, USER_INFO_TIME_UNIT);
    }

    /**
     * 获取用户token
     * @param k 键
     * @return 一般为json格式
     */
    public static String getUserToken(String k) {
        if (k == null)
            return null;
        Object object = RedisUtils.get(k);
        if (object == null)
            return null;
        return object.toString();
    }

    /**
     * 移除
     * @param k 键
     */
    public static void remove(String k) {
        RedisUtils.del(k);
    }

}
