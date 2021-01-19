package com.twj.spirngbasics.server.util;


import com.twj.spirngbasics.server.entity.User;
import org.springframework.stereotype.Component;

/**
 * @作者: JJ
 * @创建时间: 2020/7/21 下午5:31
 * @Version 1.0
 * @描述: 通过ThreadLocal缓存User信息
 */
@Component
public class UserUtils {

    private static ThreadLocal<User> userInfo = new ThreadLocal<User>();

    public static User getUser() {
        return userInfo.get();
    }

    public static void setUser(User user) {
        userInfo.set(user);
    }

    public static void remove() {
        userInfo.remove();
    }

}
