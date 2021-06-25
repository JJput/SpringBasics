package com.twj.spirngbasics.phonemessage.mq;

public class RabbitMqConfig {

    //队列名称 阿里云短信
    public final static String QUETYPE_ALIYUN = "aliyun";
    //队列名称 腾讯云
    public final static String QUETYPE_TENCENT = "tencent";
    //路由关键字，交换机根据这个进行消息发送
    public final static String ROUTINGKEY_PHONE_MSG = "phone.msg.";

    public final static String EXCHANGE_PROJECT = "com.youde";
}
