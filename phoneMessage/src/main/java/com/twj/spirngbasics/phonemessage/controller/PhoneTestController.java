package com.twj.spirngbasics.phonemessage.controller;


import com.twj.spirngbasics.phonemessage.mq.RabbitMqConsumer;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
@Slf4j
@Api(tags = "短信发送测试")
public class PhoneTestController {

    @Resource
    private RabbitProducer rabbitProducer;

    /**
     * 发送腾讯短信
     */
    @GetMapping("/sendTencent")
    @ApiOperation("腾讯发送短信测试")
    public String sendTencent(String templateCode, String phone, String code) {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_TENCENT,
                templateCode + "," + phone + "," + code);
        return "ok";
    }

    /**
     * 发送阿里短信
     */
    @GetMapping("/sendAliyun")
    @ApiOperation("阿里发送短信测试")
    public String sendAliyun(String templateCode, String phone, String code) {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_ALIYUN,
                templateCode + "," + phone + "," + code);
        return "ok";
    }

}
