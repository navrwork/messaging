package com.navr.demo.rabbitmqdemo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
