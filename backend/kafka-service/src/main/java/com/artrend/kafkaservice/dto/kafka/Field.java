package com.artrend.kafkaservice.dto.kafka;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Field {
    private String type;
    private boolean optional;
    private String field;
}