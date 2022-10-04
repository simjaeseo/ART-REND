package com.artrend.kafkaservice.dto.kafka;

import com.artrend.kafkaservice.dto.kafka.payload.MemberLogPayload;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaMemberLogDto implements Serializable {
    private Schema schema;
    private MemberLogPayload payload;
}
