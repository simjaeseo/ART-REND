package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.member.entity.Member;
import com.artrend.businessservice.domain.member.exception.MemberException;
import com.artrend.businessservice.domain.member.exception.MemberExceptionType;
import com.artrend.businessservice.domain.member.repository.MemberRepository;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LikedPaintingServiceImpl implements LikedPaintingService {
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void like(LikedPaintingDto likedPaintingDto) throws IOException {
        // 1. jwtToken 인가 받기 (auth-service 요청 필요)

        // 2. 이미 좋아요 된 그림인 경우 409 에러 호출하기
        if (findLikedPaintingWithMemberAndPaintingId(likedPaintingDto).isPresent()) {
            throw new PaintingException(PaintingExceptionType.ALREADY_LIKED_PAINTING);
        }

        // 3. 좋아요 한 그림 ID와 회원 ID로 DB에 저장하기
        LikedPainting likedPainting = LikedPainting.builder()
                .paintingId(likedPaintingDto.getPaintingId())
                .member(memberRepository.findMemberById(likedPaintingDto.getMemberId()))
                .build();

        likedPaintingRepository.save(likedPainting);

        // 4. 그림의 총 좋아요 수 증가
        updateLikeCount(likedPaintingDto.getPaintingId(), 1);
    }

    @Override
    @Transactional
    public void cancelLike(LikedPaintingDto likedPaintingDto) throws IOException {
        // 1. jwtToken 인가 받기 (auth-service 요청 필요)


        // 2. 좋아요 된 그림이 아닌 경우 409 에러 호출하기
        LikedPainting likedPainting
                = findLikedPaintingWithMemberAndPaintingId(likedPaintingDto)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_LIKED_PAINTING));

        // 3. DB 에서 좋아요 된 그림 객체 제거
        likedPaintingRepository.delete(likedPainting);

        // 4. 그림의 총 좋아요 수 감소
        updateLikeCount(likedPaintingDto.getPaintingId(), -1);
    }

    public void validateToken(LikedPaintingDto likedPaintingDto) {
        // 유효한 토큰인지 검증 (auth-service 요청 필요)
    }

    public Optional<LikedPainting> findLikedPaintingWithMemberAndPaintingId(LikedPaintingDto likedPaintingDto) {
        // 존재하는 회원이 아닌 경우 에러 호출하기
        Member findMember = memberRepository.findById(likedPaintingDto.getMemberId())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        return likedPaintingRepository
                .findLikedPaintingByMemberAndPaintingId(findMember, likedPaintingDto.getPaintingId());
    }

    public void updateLikeCount(Long paintingId, Integer count) throws IOException {
        Painting findPainting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        findPainting.updateTotalLikeCount(count);
    }
}
