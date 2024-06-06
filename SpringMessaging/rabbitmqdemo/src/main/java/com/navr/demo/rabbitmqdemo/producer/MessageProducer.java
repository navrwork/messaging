package com.navr.demo.rabbitmqdemo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    @Value("${rabbitmqdemo.exchange}")
    private String exchangeName;

    @Value("${rabbitmqdemo.routingkey.string}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    // RabbitTemplate autowired via below constructor
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceMessage(String message) {
        LOGGER.info(String.format("MessageProducer sending message: %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
