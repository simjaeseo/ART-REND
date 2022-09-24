package com.artrend.authservice.dto;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.global.auth.SHA256;
import lombok.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String name;
    private String provider;
    private String providerId;
    private String nameAttributeKey;


    public static OAuthAttributes of(String provider, String providerId, Map<String, Object> attributes){
        //kakao
        if("kakao".equals(provider)){
            return ofKakao(providerId, attributes);
        }

        // google
        return ofGoogle(providerId, attributes);
    }

    private static OAuthAttributes ofKakao(String providerId, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .attributes(attributes)
                .provider("kakao")
                .providerId(providerId)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .attributes(attributes)
                .provider("google")
                .providerId(userNameAttributeName)
                .build();
    }

    public Member toKakaoEntity(){
        return Member.builder()
                .name(name)
                .kakaoProvider(provider)
                .kakaoProviderId(providerId)
                .build();
    }

    public Member toGoogleEntity(){
        return Member.builder()
                .name(name)
                .googleProvider(provider)
                .googleProviderId(providerId)
                .build();
    }

}