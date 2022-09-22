package com.artrend.businessservice.domain.member.exception;

import com.artrend.businessservice.global.Exception.BaseException;
import com.artrend.businessservice.global.Exception.BaseExceptionType;

public class MemberException extends BaseException {
    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
