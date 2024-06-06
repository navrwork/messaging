package com.navr.demo.rabbitmqdemo.producer;

import com.navr.demo.rabbitmqdemo.dto.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JsonMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMessageProducer.class);

    private RabbitTemplate jsonRabbitTemplate;

    @Value("${rabbitmqdemo.exchange}")
    private String exchange;

    @Value("${rabbitmqdemo.routingkey.json}")
    private String jsonRoutingKey;

    @Value("${rabbitmqdemo.queue.json}")
    private String jsonQueueName;

    public JsonMessageProducer(@Qualifier("jsonRabbitTemplate") RabbitTemplate jsonRabbitTemplate) {
        this.jsonRabbitTemplate = jsonRabbitTemplate;
    }

    public void produceJsonMessage(User user) {
        jsonRabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
        LOGGER.info(String.format("produceJsonMessage finished for user obj -> %s", user.toString()));
    }
}
