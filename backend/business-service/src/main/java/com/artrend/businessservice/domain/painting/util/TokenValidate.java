package com.artrend.businessservice.domain.painting.util;

import com.artrend.businessservice.domain.member.exception.MemberException;
import com.artrend.businessservice.domain.member.exception.MemberExceptionType;
import com.artrend.businessservice.domain.painting.dto.LikeDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class TokenValidate {
    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(LikeDto likeDto, String token) {
        // 유효한 토큰인지 검증
        // secret 암호화
        afterPropertiesSet();

        // token id값 추출
        Long memberId = Long.parseLong(
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id")
                        .toString());

        log.debug("memberId -> {}", memberId);

        if (memberId == null) {
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
        }

        if (memberId != likeDto.getMemberId()) {
            throw new MemberException(MemberExceptionType.CONFLICT_MEMBER);
        }
    }

}
