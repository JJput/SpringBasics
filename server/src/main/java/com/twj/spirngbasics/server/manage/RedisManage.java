package com.twj.spirngbasics.server.manage;


import com.alibaba.fastjson.JSONObject;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.util.RedisUtils;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @作者: JJ
 * @创建时间: 2020/11/17 下午8:06
 * @Version 1.0
 * @描述:
 */
public class RedisManage {

    //用户信息缓存  3天后过期
    private static final int USER_INFO_TIME = 3;
    private static final TimeUnit USER_INFO_TIME_UNIT = TimeUnit.DAYS;

    //手机号验证码  5分钟后过期
    private static final int PHONE_CODE_TIME = 5;
    private static final TimeUnit PHONE_CODE_TIME_UNIT = TimeUnit.MINUTES;

    /**
     * 设置用户token
     *
     * @param k 键
     * @param v 值
     */
    public static void setUserToken(@NotNull String k, @NotNull User v) {
        RedisUtils.set(k, JSONObject.toJSONString(v), USER_INFO_TIME, USER_INFO_TIME_UNIT);
    }

    /**
     * 获取用户token
     *
     * @param k 键
     * @return 一般为json格式
     */
    public static String getUserToken(@NotNull String k) {
        if (k == null)
            return null;
        Object object = RedisUtils.get(k);
        if (object == null)
            return null;
        return object.toString();
    }

    /**
     * 设置手机号验证码
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public static void setPhoneCode(@NotNull String phone, @NotNull String code) {
        RedisUtils.set(phone, code, PHONE_CODE_TIME, PHONE_CODE_TIME_UNIT);
    }

    /**
     * 获取手机号验证码
     *
     * @param phone 手机号
     */
    public static String getPhoneCode(@NotNull String phone) {
        Object object = RedisUtils.get(phone);
        if (object == null)
            return null;
        return object.toString();
    }


    /**
     * 移除
     *
     * @param k 键
     */
    public static void remove(@NotNull String k) {
        RedisUtils.del(k);
    }


    /**
     * 设置角色权限
     * @param id
     * @param map
     */
    public static void setRoleResource(String id, Map<String, Object> map) {
        if (id == null) {
            return;
        }
        if (map.isEmpty()) {
            return;
        }
        RedisUtils.setHash(id, map);
    }

    /**
     * 移除角色权限
     * @param id
     */
    public static void delRoleResource(String id) {
        if (id == null) {
            return;
        }
        RedisUtils.del(id);
    }
}
