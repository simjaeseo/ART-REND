package com.artrend.authservice.dto;

import com.artrend.authservice.global.common.MessageResponse;
import lombok.Data;

@Data
public class PaintingDtoDataResponse<T> extends MessageResponse {
    private T data;

    public PaintingDtoDataResponse(String message) {
        super(message);
    }

    public PaintingDtoDataResponse() {
        super();
    }
}
