package com.twj.spirngbasics.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RabbitProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    /**
     * 消息发送
     *
     * @param exchange   交换机
     * @param routingkey 路由规则，可以使用路由关键字+队列名进行指定发送，也可以路由关键字#(一个或多个) *(一个) 进行该路由关键字下全发
     * @param content    发送内容
     */
    public void sendMsg(String exchange, String routingkey, String content, String id) {
        //消息id，主要用于消息发送完成后，回调根据id进行区分具体是那个消息成功了
        CorrelationData correlationId = new CorrelationData(id);
        rabbitTemplate.convertAndSend(exchange, routingkey, content, correlationId);
    }

    /**
     * 消息发送
     *
     * @param exchange   交换机
     * @param routingkey 路由规则，可以使用路由关键字+队列名进行指定发送，也可以路由关键字#(一个或多个) *(一个) 进行该路由关键字下全发
     * @param content    发送内容
     * @return 返回消息id
     */
    public String sendMsg(String exchange, String routingkey, String content) {
        //消息id，主要用于消息发送完成后，回调根据id进行区分具体是那个消息成功了
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(exchange, routingkey, content, correlationId);
        return uuid;
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        logger.info(" 回调id:" + correlationData);
//        if (ack) {
//            logger.info("消息成功消费");
//        } else {
//            logger.info("消息消费失败:" + cause);
//        }
    }
}