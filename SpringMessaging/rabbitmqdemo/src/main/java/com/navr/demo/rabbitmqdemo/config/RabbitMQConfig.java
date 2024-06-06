package com.navr.demo.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Below objects are automatically created
    // by the Spring Boot container.
    // We just need to inject and make use of it.
    //
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
    //

    @Value("${rabbitmqdemo.exchange}")
    private String exchange;

    @Value("${rabbitmqdemo.queue.string}")
    private String queueString;

    @Value("${rabbitmqdemo.routingkey.string}")
    private String routingKeyString;

    @Value("${rabbitmqdemo.queue.json}")
    private String queueJson;

    @Value("${rabbitmqdemo.routingkey.json}")
    private String routingKeyJson;

    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queueString() {
        return new Queue(queueString);
    }

    @Bean
    public Queue queueJson() {
        return new Queue(queueJson);
    }

    @Bean
    public Binding bindingStringQ() {
        return BindingBuilder
                .bind(queueString())
                .to(topicExchange())
                .with(routingKeyString);
    }

    @Bean
    public Binding bindingJsonQ() {
        return BindingBuilder
                .bind(queueJson())
                .to(topicExchange())
                .with(routingKeyJson);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
        jsonRabbitTemplate.setMessageConverter(converter());
        return jsonRabbitTemplate;
    }
}
