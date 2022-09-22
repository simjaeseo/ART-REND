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

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    // 소셜 로그인 완료 후 소셜로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 구글 로그인 버튼 클릭 > 구글 로구인창 > 로그인 완료 > code를 리턴 (OAuth-client 라이브러리가 대신 받아줌) > 액세스토큰 요청
    // userRequest 정보를 받아옴 > 해당 정보를 받아오면서 loadUser 함수 호출함 > 이 함수에서 소셜로부터 회원프로필을 받아줌
   @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
       System.out.println(111111111);
        // 아마, UserRequest 데이터에서 OAuth로부터 받아온 유저데이터(OAuth2User)만 뽑아내는 부분같음
       OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
       // 어떤 OAuth2 서비스로 로그인했는지 확인가능 (provider)
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
       System.out.println("oAuth2User.getAttributes() : " + oAuth2User.getAttributes());
        // 구글 or 카카오에 따라 사용자 정보 파싱하는 키가 다름
       // 소셜로부터 받아온 사용자 이름 + provider, providerId
        OAuthAttributes attributes = OAuthAttributes.of(provider, providerId, oAuth2User.getAttributes());

       Optional<Member> member = null;
       if(provider.equals("google")){
           member = memberRepository.findByKakaoProviderId(attributes.getProviderId());
       }else if(provider.equals("kakao")){
           member = memberRepository.findByKakaoProviderId(attributes.getProviderId());
       }


       // 해당 소셜로 회원가입이 한적 없다면,
       if(!member.isPresent()) {
           // DI를 요청

           String di = attributes.getDi(attributes.getName());

           Optional<Member> findMemberbyDi = memberRepository.findByDi(di);

           // di로 테이블 검색했는데도 멤버가 없으면
           if(!findMemberbyDi.isPresent()){
               // 회원가입 진행
               if(provider.equals("kakao")){
                   memberRepository.save(attributes.toKakaoEntity());
               }else{
                   memberRepository.save(attributes.toGoogleEntity());
               }
           }else{
               // di로 검색했는데 멤버가 존재한다면 해당 컬럼에 소셜 정보 인서트
               findMemberbyDi.get().updateProviderAndProviderId(provider, providerId);
           }
       }

        return new DefaultOAuth2User(null,
                attributes.getAttributes(),
                userNameAttributeName);
    }

//    // 유저 생성 및 수정 서비스 로직
//    private Member saveUser(OAuthAttributes attributes){
//       // 사용자 회원가입되어있는지 확인하기
//        Optional<Member> member = memberRepository.findByProviderId(attributes.getProviderId());
//
//        // 존재하지않으면
//        if(member == null){
//            //회원가입
//            return memberRepository.save(attributes.toEntity());
//        }else{
//            // 이미 회원가입이 되어있으면
//            return member.get();
//        }
//    }
}