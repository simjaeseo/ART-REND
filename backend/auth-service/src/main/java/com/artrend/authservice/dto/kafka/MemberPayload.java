package com.artrend.authservice.dto.kafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberPayload {
    private Long id;

    private String name;

    private String nickname;

    private String kakao_provider;

    private String kakao_provider_id;

    private String google_provider;

    private String google_provider_id;
}
