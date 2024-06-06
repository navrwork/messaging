package com.navr.demo.rabbitmqdemo.controller;

import com.navr.demo.rabbitmqdemo.consumer.MessageConsumer;
import com.navr.demo.rabbitmqdemo.dto.BaseResponseDto;
import com.navr.demo.rabbitmqdemo.dto.User;
import com.navr.demo.rabbitmqdemo.producer.JsonMessageProducer;
import com.navr.demo.rabbitmqdemo.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private JsonMessageProducer jsonMessageProducer;

    @GetMapping(value = "/produceMessage/{message}")
    public ResponseEntity<String> produceMessage(@PathVariable("message") String message) {
        messageProducer.produceMessage(message);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Message sent -> %s", message));
    }

    @PostMapping(value = "/produceJsonMessage")
    public ResponseEntity<BaseResponseDto> produceJsonMessage(@RequestBody User user) {
        jsonMessageProducer.produceJsonMessage(user);
        BaseResponseDto baseResponseDto = new BaseResponseDto(HttpStatus.OK.value(), "produceJsonMessage successful.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(baseResponseDto);
    }

}
