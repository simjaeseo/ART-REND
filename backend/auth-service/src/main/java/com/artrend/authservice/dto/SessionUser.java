package com.artrend.authservice.dto;

import com.artrend.authservice.domain.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 User클래스
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String provider;
    private String providerId;

    public SessionUser(Member member){
        this.name = member.getName();
        this.provider = member.getProvider();
        this.providerId = member.getProviderId();
    }
}