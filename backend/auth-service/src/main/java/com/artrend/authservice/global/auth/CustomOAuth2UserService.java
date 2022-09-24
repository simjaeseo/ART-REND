package com.artrend.authservice.global.auth;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.dto.OAuthAttributes;
import com.artrend.authservice.repository.MemberRepository;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    // 소셜 로그인 완료 후 소셜로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 구글 로그인 버튼 클릭 > 구글 로구인창 > 로그인 완료 > code를 리턴 (OAuth-client 라이브러리가 대신 받아줌) > 액세스토큰 요청
    // userRequest 정보를 받아옴 > 해당 정보를 받아오면서 loadUser 함수 호출함 > 이 함수에서 소셜로부터 회원프로필을 받아줌
   @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        // 아마, UserRequest 데이터에서 OAuth로부터 받아온 유저데이터(OAuth2User)만 뽑아내는 부분같음
       OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        // 카카오 = id, 구글 = sub으로 저장됨
       String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

       String providerId = "";

        if(provider.equals("google")){
            // OAuth2 로그인 진행 시 키가 되는 필드 값(PK) (providerId = sub)
            providerId = oAuth2User.getAttributes().get("sub").toString();
        }else if(provider.equals("kakao")){
            providerId = oAuth2User.getAttributes().get("id").toString();
        }

        // 구글 or 카카오에 따라 사용자 정보 파싱하는 키가 다름
       // 소셜로부터 받아온 사용자 이름 + provider, providerId
        OAuthAttributes attributes = OAuthAttributes.of(provider, providerId, oAuth2User.getAttributes());

       Optional<Member> member = null;
       if(provider.equals("google")){
           member = memberRepository.findByGoogleProviderId(attributes.getProviderId());

           if(!member.isPresent()){
               Optional<Member> findMemberByProvider = memberRepository.findByKakaoProvider("kakao");

               // 카카오로 로그인한적이 있다면
               if(findMemberByProvider.isPresent()){
                   findMemberByProvider.get().updateProviderAndProviderId(provider, providerId);
               }else{
                   memberRepository.save(attributes.toGoogleEntity());
               }
           }

       }else if(provider.equals("kakao")){
           member = memberRepository.findByKakaoProviderId(attributes.getProviderId());

           if(!member.isPresent()){
               Optional<Member> findMemberByProvider = memberRepository.findByGoogleProvider("google");

               // 구글로 로그인한적이 있다면
               if(findMemberByProvider.isPresent()){
                   findMemberByProvider.get().updateProviderAndProviderId(provider, providerId);
               }else{
                   memberRepository.save(attributes.toKakaoEntity());
               }
           }
       }
       // 다른 소셜로 가입한 상태에서 나머지 소셜로 첫 로그인했을때,


        return new DefaultOAuth2User(null,
                attributes.getAttributes(),
                userNameAttributeName);
    }

}