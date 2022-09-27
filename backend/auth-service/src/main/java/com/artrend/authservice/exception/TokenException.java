package com.artrend.authservice.exception;

import com.artrend.authservice.global.exception.BaseException;
import com.artrend.authservice.global.exception.BaseExceptionType;

public class TokenException extends BaseException {
    private BaseExceptionType exceptionType;

    public TokenException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}