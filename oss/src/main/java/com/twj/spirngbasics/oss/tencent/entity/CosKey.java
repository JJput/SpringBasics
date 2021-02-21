package com.twj.spirngbasics.oss.tencent.entity;

import lombok.Data;

/**
 * @作者: JJ
 * @创建时间: 2020/8/19 下午9:33
 * @Version 1.0
 * @描述:
 */
@Data
public class CosKey {

    private CredentialsBean credentials;
    private String requestId;
    private String expiration;
    private int startTime;
    private int expiredTime;

    @Data
    public static class CredentialsBean {

        private String tmpSecretId;
        private String tmpSecretKey;
        private String sessionToken;
    }
}
