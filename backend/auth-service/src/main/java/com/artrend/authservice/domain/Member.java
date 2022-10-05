package com.artrend.authservice.domain;

import com.artrend.authservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String nickname;

    private String kakaoProvider;

    private String kakaoProviderId;

    private String googleProvider;

    private String googleProviderId;

    private String di;


    public void updateProviderAndProviderId(String provider, String providerId){

        if(provider.equals("kakao")){
            this.kakaoProvider= provider;
            this.kakaoProviderId = providerId;
        }else{
            this.googleProvider =provider;
            this.googleProviderId = providerId;
        }
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

}