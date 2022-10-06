package com.artrend.authservice.service;

import com.artrend.authservice.global.jwt.TokenProvider;
import com.artrend.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public void logout(String accessToken) {
        String providerId = tokenProvider.getMemberProviderId(accessToken);
    }


}
