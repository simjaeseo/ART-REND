package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.member.client.MemberServiceClient;
import com.artrend.businessservice.domain.member.exception.MemberException;
import com.artrend.businessservice.domain.member.exception.MemberExceptionType;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Key;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LikedPaintingServiceImpl implements LikedPaintingService {
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;

    private final MemberServiceClient memberServiceClient;
    @Value("${jwt.secret}")
    private String secret;
    private Key key;
    private Long memberId;

    @Override
    @Transactional
    public void like(LikedPaintingDto likedPaintingDto, String token) throws IOException {
        // 1. jwtToken 인가 받기 (auth-service 요청 필요)
        validateToken(likedPaintingDto, token);

        // 2. 이미 좋아요 된 그림인 경우 409 에러 호출하기
        if (findLikedPaintingWithMemberAndPaintingId(likedPaintingDto).isPresent()) {
            throw new PaintingException(PaintingExceptionType.ALREADY_LIKED_PAINTING);
        }

        // 3. 좋아요 한 그림 ID와 회원 ID로 DB에 저장하기
        LikedPainting likedPainting = LikedPainting.builder()
                .paintingId(likedPaintingDto.getPaintingId())
//                .member(memberRepository.findMemberById(likedPaintingDto.getMemberId()))
                .build();

        likedPaintingRepository.save(likedPainting);

        // 4. 그림의 총 좋아요 수 증가
        updateLikeCount(likedPaintingDto.getPaintingId(), 1);
    }

    @Override
    @Transactional
    public void cancelLike(LikedPaintingDto likedPaintingDto, String token) throws IOException {
        // 1. jwtToken 인가 받기 (auth-service 요청 필요)
        validateToken(likedPaintingDto, token);

        // 2. 좋아요 된 그림이 아닌 경우 409 에러 호출하기
        LikedPainting likedPainting
                = findLikedPaintingWithMemberAndPaintingId(likedPaintingDto)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_LIKED_PAINTING));

        // 3. DB 에서 좋아요 된 그림 객체 제거
        likedPaintingRepository.delete(likedPainting);

        // 4. 그림의 총 좋아요 수 감소
        updateLikeCount(likedPaintingDto.getPaintingId(), -1);
    }

    public void validateToken(LikedPaintingDto likedPaintingDto, String token) {
        // 유효한 토큰인지 검증 (auth-service 요청 필요)

        // secret 암호화
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        System.out.println(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        System.out.println(this.key.toString());

        // token id값 추출
        Long memberId = Long.parseLong(
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id")
                        .toString());

        if (memberId == null) {
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
        }
        log.debug("memberId -> {}", memberId);
    }

    // 회원 ID와 그림 ID로 좋아요 여부 확인
    public Optional<LikedPainting> findLikedPaintingWithMemberAndPaintingId(LikedPaintingDto likedPaintingDto) {
        return likedPaintingRepository
                .findByMemberIdAndPaintingId(memberId, likedPaintingDto.getPaintingId());
    }

    public void updateLikeCount(Long paintingId, Integer count) throws IOException {
        Painting findPainting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        findPainting.updateTotalLikeCount(count);
    }

}
