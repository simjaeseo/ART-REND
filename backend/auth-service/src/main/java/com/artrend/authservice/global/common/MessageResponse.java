package com.artrend.authservice.global.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponse<T> {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}