package com.artrend.businessservice.domain.painting.client;

import com.artrend.businessservice.domain.painting.vo.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface UserServiceClient {

    @GetMapping("/auth-service/members/{memberId}")
    MemberResponse getMember(@PathVariable("memberId") Long id);
}
