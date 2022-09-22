package com.artrend.businessservice.domain.member.client;

import com.artrend.businessservice.domain.member.vo.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface MemberServiceClient {

    @GetMapping("/auth-service/members/{memberId}")
    MemberResponse getMemberId(@PathVariable("memberId") Long id);
}
