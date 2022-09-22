package com.artrend.authservice.global.config;

import com.artrend.authservice.global.auth.OAuth2AuthenticationSuccessHandler;
import com.artrend.authservice.global.auth.OAuth2LogoutSuccessHandler;
import com.artrend.authservice.global.auth.CustomOAuth2UserService;
import com.artrend.authservice.global.jwt.JwtAccessDeniedHandler;
import com.artrend.authservice.global.jwt.JwtAuthenticationEntryPoint;
import com.artrend.authservice.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import javax.ws.rs.HttpMethod;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler;

    private static final String[] PERMIT_URL_ARRAY = {
            "/favicon.ico",
            /* custom oauth api */
            "/oauth2/authorization/kakao",
            "/test",
            "/error",
            "/health_check",
            "/logout",
            "/token/reissuance/*",
            "/signup/**"
    };



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.csrf().disable();
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .logout()
                .logoutSuccessUrl("/logout-success")
                .logoutSuccessHandler(oAuth2LogoutSuccessHandler)

                .and()
                    .oauth2Login()
                        .defaultSuccessUrl("/login-success")
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .userInfoEndpoint() // oauth2 로그인 성공 후 가져올 때의 설정들
                            // 소셜로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                            .userService(customOAuth2UserService); // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
        http
                .authorizeRequests()// URL별 권한 권리
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3001/", "https://youthful-kirch-818851.netlify.app/", "https://ssafymate.site/", "https://www.ssafymate.site/", "https://i6a402.p.ssafy.io/%22"));
//                configuration.addAllowedHeader("");
//        configuration.addAllowedMethod("");
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//
//    }
}