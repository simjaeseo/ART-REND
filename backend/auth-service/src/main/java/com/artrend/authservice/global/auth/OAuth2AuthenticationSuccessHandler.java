package com.artrend.authservice.global.auth;

import com.artrend.authservice.client.BusinessServiceClient;
import com.artrend.authservice.domain.Member;
import com.artrend.authservice.dto.PaintingDtoDataResponse;
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
    private final BusinessServiceClient businessServiceClient;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        System.out.println("oAuth2User.getAttributes() " + oAuth2User.getAttributes());
        String provider = String.valueOf(oAuth2User.getAttributes().get("provider"));
        String providerId = "";
        Optional<Member> findMember = null;

        // 토큰 발급하기위해서는 memberId를 알아야하지만, 카카오로 로그인했는지, 구글로 로그인했는지 모르기때문에 if 문으로 나누기
        // 카카오로 로그인했을때
        if (oAuth2User.getAttributes().get("id") == null) {
            providerId = oAuth2User.getAttributes().get("sub").toString();
            findMember = memberRepository.findByGoogleProviderId(providerId);
        } else {
            providerId = oAuth2User.getAttributes().get("id").toString();
            findMember = memberRepository.findByKakaoProviderId(providerId);
        }

        boolean isExisted = false;
        boolean isSelectPainting = false;
        boolean isNickname = false;

        if (findMember.isPresent()) {
            // 멤버가 존재하면
            isExisted = true;

            if (findMember.get().getNickname() != null) {
                isNickname = true;
            }

            PaintingDtoDataResponse paintingDtoDataResponse = businessServiceClient.getSelectedPaintingList(findMember.get().getId());
            // 이미 회원가입 + 닉네임까지 추가 + 선호그림은 선택 x
            if (!paintingDtoDataResponse.getData().toString().equals("[]")) {
                isSelectPainting = true;
            }
        }

        String url = makeRedirectUrl(provider, providerId, isExisted, isSelectPainting, isNickname, findMember);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String provider, String providerId, boolean isExisted, boolean isSelectPainting, boolean isNickname, Optional<Member> findMember) {

        if (isExisted == true && isSelectPainting == true && isNickname == true) {
            String accessToken = tokenProvider.createToken(providerId, findMember.get().getId());

            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
                    .queryParam("provider", provider)
                    .queryParam("providerId", providerId)
                    .queryParam("isExisted", isExisted)
                    .queryParam("isSelectPainting", isSelectPainting)
                    .queryParam("isNickname", isNickname)
                    .queryParam("accessToken", accessToken)
                    .build().toUriString();
        }

        return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
                .queryParam("provider", provider)
                .queryParam("providerId", providerId)
                .queryParam("isExisted", isExisted)
                .queryParam("isSelectPainting", isSelectPainting)
                .queryParam("isNickname", isNickname)
                .build().toUriString();
    }

}
