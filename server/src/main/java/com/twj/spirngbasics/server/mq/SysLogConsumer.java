package com.twj.spirngbasics.server.mq;

import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.service.SysLogService;
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
 * @author dean.lee
 */
@Component
public class SysLogConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SysLogConsumer.class);

    @Resource
    private SysLogService logService;

    /**
     * 接收Log队列消息
     * value: @Queue 注解，用于声明队列，value 为 queueName, durable 表示队列是否持久化, autoDelete 表示没有消费者之后队列是否自动删除
     * exchange: @Exchange 注解，用于声明 exchange， type 指定消息投递策略，我们这里用的 topic 方式 ，ignoreDeclarationExceptions=true 当队列不存在时不结束其他队列
     * key: 在 topic 方式下，这个就是我们熟知的 routingKey
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqConfig.QUETYPE_LOG, durable = "false"),
            exchange = @Exchange(
                    value = RabbitMqConfig.EXCHANGE_LOG,
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {RabbitMqConfig.ROUTINGKEY_LOG}
    ))
    public void ListenLogQueue(String msg) {
        logService.save(msg);
    }

}
