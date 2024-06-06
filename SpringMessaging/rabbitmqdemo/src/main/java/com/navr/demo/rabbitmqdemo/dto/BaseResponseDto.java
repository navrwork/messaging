package com.navr.demo.rabbitmqdemo.dto;

import lombok.Data;

@Data
public class BaseResponseDto {
    private int statusCode;
    private String message;

    public BaseResponseDto() {

    }

    public BaseResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
