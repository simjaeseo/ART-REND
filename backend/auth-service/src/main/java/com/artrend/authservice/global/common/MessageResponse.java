package com.artrend.authservice.global.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponse<T> {

    private HttpStatus httpStatus;
    private String message;

    public MessageResponse() {
        this.httpStatus = HttpStatus.OK;
        this.message = "success";
    }
}