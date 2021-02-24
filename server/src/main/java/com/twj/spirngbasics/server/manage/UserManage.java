package com.twj.spirngbasics.server.manage;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.util.Constant;
import com.twj.spirngbasics.server.util.EncryptUtils;
import com.twj.spirngbasics.server.util.UserUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @作者: JJ
 * @创建时间: 2021/1/4 下午3:06
 * @Version 1.0
 * @描述:
 */
@Slf4j
public class UserManage {

    private static final String MD5_TOKEN_END = "com.twj.spirngbasics";

    public static String getUserId() {
        User user = UserUtils.getUser();
        if (user != null)
            return user.getId();
        return null;
    }

    public static User getUser() {
        return UserUtils.getUser();
    }

    public static void setUser(String token) {
        String userStr = RedisManage.getUserToken(token);
        UserUtils.setUser(anayJson(userStr));
    }

    /**
     * 将userjson数据解析成user实体类
     *
     * @param object
     * @return
     */
    private static User anayJson(Object object) {
        if (object == null) {
            log.info("token is null");
            throw new BusinessException(Constant.Http.ERROR.TOKEN_IS_NULL);
        }
        User user;
        try {
            user = JSON.parseObject(object.toString(), User.class);
            return user;
        } catch (Exception e) {
            throw new BusinessException(Constant.Http.ERROR.TOKEN_ERROR);
        }

    }

    /**
     * token加密
     *
     * @param token
     * @return
     */
    public static String tokenEncryption(String token) {
        String newToken = EncryptUtils.encryptMD5ToString(token + MD5_TOKEN_END).substring(6, 30);
        log.info("token = " + token + "   newToken = " + newToken);
        return newToken;
    }

    /**
     * 密码加密
     *
     * @param token
     * @return
     */
    public static String pwdEncryption(String token) {
        String newToken = EncryptUtils.encryptMD5ToString(token + MD5_TOKEN_END);
        return newToken;
    }

}
