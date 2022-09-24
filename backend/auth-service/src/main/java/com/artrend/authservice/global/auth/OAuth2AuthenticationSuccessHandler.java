package com.artrend.authservice.global.auth;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.global.jwt.TokenProvider;
import com.artrend.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final Environment env;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        System.out.println("oAuth2User.getAttributes() "+ oAuth2User.getAttributes());
        String providerId = "";
        Member findMember = null;

        // 토큰 발급하기위해서는 memberId를 알아야하지만, 카카오로 로그인했는지, 구글로 로그인했는지 모르기때문에 if문으로 나누기
        // 카카오로 로그인했을때
        if( oAuth2User.getAttributes().get("id") == null){
            providerId = oAuth2User.getAttributes().get("sub").toString();
            findMember = memberRepository.findByGoogleProviderId(providerId).orElseThrow(() -> new RuntimeException("멤버익셉션으로 구현하자"));
        }else{
            providerId = oAuth2User.getAttributes().get("id").toString();
            findMember = memberRepository.findByKakaoProviderId(providerId).orElseThrow(() -> new RuntimeException("멤버익셉션으로 구현하자"));
        }

        String accessToken = tokenProvider.createToken(providerId, findMember.getId());

        String url = makeRedirectUrl(accessToken, oAuth2User);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String accessToken, OAuth2User oAuth2User) {

        if((boolean) oAuth2User.getAttributes().get("isSelectPainting")){
            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
                    .queryParam("accessToken", accessToken)
                    .queryParam("isSelectPainting", true)
                    .build().toUriString();
        }else{
            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
                    .queryParam("accessToken", accessToken)
                    .queryParam("isSelectPainting", false)
                    .build().toUriString();
        }
    }

}
