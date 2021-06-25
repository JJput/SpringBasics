package com.twj.spirngbasics.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq入门：https://www.cnblogs.com/sgh1023/p/11217017.html
 * SpringBoot集成RabbitMq：https://blog.csdn.net/qq_38455201/article/details/80308771
 * https://blog.csdn.net/qq_35387940/article/details/100514134
 */
@SuppressWarnings("ALL")
@Configuration
public class RabbitMqConfig {


    //交换机
    public final static String EXCHANGE_PROJECT = "com.twj.spirngbasics";
    //队列名称
    public final static String QUETYPE_LOG = "log";
    //路由关键字，交换机根据这个进行消息发送
    public final static String ROUTINGKEY_LOG = "system." + QUETYPE_LOG;

    @Bean
    public Queue queueLog() {
        //队列名
        return new Queue(QUETYPE_LOG, false);
    }

    @Bean
    TopicExchange exchangeProject() {
        //创建交换器
        return new TopicExchange(EXCHANGE_PROJECT);
    }

    @Bean
    Binding bindingLog() {
        return BindingBuilder.bind(queueLog()).to(exchangeProject()).with(ROUTINGKEY_LOG);
    }


    //队列名称 阿里云短信
    public final static String QUETYPE_ALIYUN = "aliyun";
    //队列名称 腾讯云短信
    public final static String QUETYPE_TENCENT = "tencent";
    //路由关键字，交换机根据这个进行消息发送
    public final static String ROUTINGKEY_PHONE_MSG = "phone.msg.";

    @Bean
    public Queue queueAliyun() {
        //队列名
        return new Queue(QUETYPE_ALIYUN, false);
    }

    @Bean
    public Queue queueTencent() {
        //队列名
        return new Queue(QUETYPE_TENCENT, false);
    }

    @Bean
    Binding bindingAliyun() {
        return BindingBuilder.bind(queueAliyun()).to(exchangeProject()).with(ROUTINGKEY_PHONE_MSG);
    }

    @Bean
    Binding bindingTencent() {
        return BindingBuilder.bind(queueTencent()).to(exchangeProject()).with(ROUTINGKEY_PHONE_MSG);
    }
}
