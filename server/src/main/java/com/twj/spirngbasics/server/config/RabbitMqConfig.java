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
 *                        https://blog.csdn.net/qq_35387940/article/details/100514134
 */
@SuppressWarnings("ALL")
@Configuration
public class RabbitMqConfig {


    //交换机
    public final static String EXCHANGE_LOG = "com.twj.spirngbasics";
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
    TopicExchange exchangeLog() {
        //创建交换器
        return new TopicExchange(EXCHANGE_LOG);
    }

    @Bean
    Binding bindingLog() {
        return BindingBuilder.bind(queueLog()).to(exchangeLog()).with(ROUTINGKEY_LOG);
    }

}
