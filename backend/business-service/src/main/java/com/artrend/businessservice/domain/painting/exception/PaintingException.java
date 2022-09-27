package com.artrend.businessservice.domain.painting.exception;

import com.artrend.businessservice.global.Exception.BaseException;
import com.artrend.businessservice.global.Exception.BaseExceptionType;

public class PaintingException extends BaseException {
    private BaseExceptionType exceptionType;

    public PaintingException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
