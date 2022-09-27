package com.artrend.businessservice.domain.painting.exception;

import com.artrend.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum PaintingExceptionType implements BaseExceptionType {
    NOT_FOUND_PAINTING(404, HttpStatus.NOT_FOUND, "그림 정보가 없습니다."),
    ALREADY_LIKED_PAINTING(409, HttpStatus.CONFLICT, "이미 좋아요한 그림입니다."),
    NOT_LIKED_PAINTING(409, HttpStatus.CONFLICT, "좋아요한 그림이 아닙니다."),
    CONFLICT_INFORMATION(409, HttpStatus.CONFLICT, "회원 정보가 유효하지 않습니다."),
    TOO_MANY_ARGUMENTS(409, HttpStatus.CONFLICT, "3개 이상의 그림은 선택할 수 없습니다.");

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
