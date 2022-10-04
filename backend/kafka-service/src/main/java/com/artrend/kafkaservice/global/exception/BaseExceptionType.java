package com.artrend.kafkaservice.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    HttpStatus getHttpStatus();
    String getErrorMessage();
}
