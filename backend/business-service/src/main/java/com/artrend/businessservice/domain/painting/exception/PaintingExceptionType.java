package com.artrend.businessservice.domain.painting.exception;

import com.artrend.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum PaintingExceptionType implements BaseExceptionType {
    NOT_FOUND_PAINTING(404, HttpStatus.NOT_FOUND, "그림 정보가 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    PaintingExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
