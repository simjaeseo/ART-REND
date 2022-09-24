package com.artrend.authservice.global.auth;

import com.artrend.authservice.domain.Member;
import lombok.*;

import java.util.HashMap;
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

        Map<String, Object> CustomAttributes = new HashMap<>();
        for ( String key : attributes.keySet() ) {
            CustomAttributes.put(key, attributes.get(key));
        }

        CustomAttributes.put("isSelectPainting", false);
        CustomAttributes.put("isAddNickname", false);

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .attributes(CustomAttributes)
                .provider("kakao")
                .providerId(providerId)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> CustomAttributes = new HashMap<>();
        for ( String key : attributes.keySet() ) {
            CustomAttributes.put(key, attributes.get(key));
        }
        CustomAttributes.put("isSelectPainting", false);
        CustomAttributes.put("isAddNickname", false);

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .attributes(CustomAttributes)
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

    // puy
    public void putIsSelectPaintings (boolean isSelectPainting){
        this.attributes.put("isSelectPainting", isSelectPainting);
    }

    public void putIsAddNickname (boolean isAddNickname){
        this.attributes.put("isAddNickname", isAddNickname);
    }

}