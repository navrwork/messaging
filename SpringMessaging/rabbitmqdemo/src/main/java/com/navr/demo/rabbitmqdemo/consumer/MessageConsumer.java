package com.navr.demo.rabbitmqdemo.consumer;

import com.navr.demo.rabbitmqdemo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmqdemo.queue.string}"})
    public void onNewStringMessage(String message) {
        LOGGER.info(String.format("MessageConsumer consumed new string message -> %s", message));
    }

    @RabbitListener(queues = {"${rabbitmqdemo.queue.json}", })
    public void onNewJsonMessage(User user) {
        LOGGER.info(String.format("MessageConsumer consumed new json message -> %s", user.toString()));
    }
}
