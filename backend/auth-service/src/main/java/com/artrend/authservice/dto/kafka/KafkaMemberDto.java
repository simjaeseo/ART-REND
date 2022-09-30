package com.artrend.authservice.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaMemberDto implements Serializable {
    private Schema schema;
    private MemberPayload payload;
}
