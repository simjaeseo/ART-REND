package com.artrend.kafkaservice.dto.kafka.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLogPayload {
    private Long member_id;
    private Long painting_id;
    private Long click_count;
    private Long zoom_count;
    private Long change_count;
    private String in_time;
    private String out_time;
}
