package com.twj.spirngbasics.phonemessage.mq;


import com.twj.spirngbasics.phonemessage.msg.AliyunSendSms;
import com.twj.spirngbasics.phonemessage.msg.TencentSendSms;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author jjput
 * @Date 2021-02-05 10:46:50
 * @Version 1.0
 * @Describe 消息接收队列
 */
@Component
public class RabbitMqConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @Resource
    private AliyunSendSms aliyunSendSms;

    @Resource
    private TencentSendSms tencentSendSms;

    /**
     * 阿里云发送手机验证码短信队列
     *
     * @param msg 数据传输格式自行定义
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqConfig.QUETYPE_ALIYUN, durable = "false"),
            exchange = @Exchange(
                    value = RabbitMqConfig.EXCHANGE_PROJECT,
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_ALIYUN}
    ))
    public void ListenAliyunTopic(String msg) {
        try {
            String[] data = msg.split(",");
            aliyunSendSms.sendCode(data[0], data[1], data[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.error("PhoneMessage-Aliyun: msg error!");
        }

    }

    /**
     * 腾讯云发送手机验证码短信队列
     *
     * @param msg 数据传输格式自行定义
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqConfig.QUETYPE_TENCENT, durable = "false"),
            exchange = @Exchange(
                    value = RabbitMqConfig.EXCHANGE_PROJECT,
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_TENCENT}
    ))
    public void ListenTencentTopic(String msg) {
        try {
            String[] data = msg.split(",");
            tencentSendSms.sendChina(data[0], data[1], data[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.error("PhoneMessage-Tencent: msg error!");
        }
    }
}
