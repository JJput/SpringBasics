package com.twj.spirngbasics.phonemessage.controller;


import com.twj.spirngbasics.phonemessage.mq.RabbitMqConsumer;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class PhoneTestController {

    @Resource
    private RabbitProducer rabbitProducer;

    /**
     * 发送腾讯短信
     */
    @GetMapping("/sendTencent")
    public String sendTencent() {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_TENCENT,
                "12312");
        return "ok";
    }

    /**
     * 发送阿里短信
     */
    @GetMapping("/sendAliyun")
    public String sendAliyun() {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_ALIYUN,
                "12312");
        return "ok";
    }

}
