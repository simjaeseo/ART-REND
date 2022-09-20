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
        String providerId = oAuth2User.getAttributes().get("id").toString();

        Member findMember = memberRepository.findByProviderId(providerId).orElseThrow(() -> new RuntimeException("멤버익셉션으로 구현하자"));

        String accessToken = tokenProvider.createToken(providerId, findMember.getId());

        String url = makeRedirectUrl(accessToken);;

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String accessToken) {
        return UriComponentsBuilder.fromUriString("http://localhost:3002/detail")
                .queryParam("accessToken", accessToken)
//                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
    }
}
