package com.twj.spirngbasics.server.config;

public class PhoneCodeConfig {

    /**
     * 手机验证码缓存配置
     */
    public interface VerificationCode {
        //初始化数量
        int INIT_NUM = 1000;
        //最大缓存数
        int MAX_SIZE = 10000 * 1;
        //过期时间 单位:分
        int EXPIRED_TIME = 5;
    }

    /**
     * 邮箱验证码缓存配置
     */
    public interface EmailCode {
        //初始化数量
        int INIT_NUM = 100;
        //最大缓存数
        int MAX_SIZE = 10000 * 2;
        //过期时间 单位:分
        int EXPIRED_TIME = 60 * 2;
    }


}
