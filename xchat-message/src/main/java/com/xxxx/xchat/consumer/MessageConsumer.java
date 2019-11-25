package com.xxxx.xchat.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-25 21:48
 */
@Component
public class MessageConsumer {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "messageSave", durable = "true"),
            exchange = @Exchange(value = "message", durable = "true", type = "direct"),
            key = "save"))
    public void messageSave(Message message, Channel channel) throws Exception {
        Object payload = message.getPayload();
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 手工ACK
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "messageRead", durable = "true"),
            exchange = @Exchange(value = "message", durable = "true", type = "direct"),
            key = "read"))
    public void messageRead(Message message, Channel channel) throws Exception {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
