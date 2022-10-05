package com.artrend.businessservice.domain.member.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth-service")
public interface MemberServiceClient {
}
