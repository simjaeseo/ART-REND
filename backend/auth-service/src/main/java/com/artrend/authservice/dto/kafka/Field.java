package com.artrend.authservice.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Field {

    private String type;
    private boolean optional;
    private String field;
}