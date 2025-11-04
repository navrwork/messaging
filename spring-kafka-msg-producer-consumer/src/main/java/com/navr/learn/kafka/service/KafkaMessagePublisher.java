package com.navr.learn.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String msg) {
        CompletableFuture<SendResult<String, Object>> future = template.send("my-topic", msg);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.printf("Sent message=[%s] with offset=[%d]", msg, result.getRecordMetadata().offset());
            } else {
                System.out.printf("Unable to send message=[%s] due to : %s", msg, ex.getMessage());
            }
        });
    }
}
