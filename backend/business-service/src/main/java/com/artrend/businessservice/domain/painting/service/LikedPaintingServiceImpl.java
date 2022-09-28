package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;

import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LikedPaintingServiceImpl implements LikedPaintingService {
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;
    private final WebClient webClient;

    @Override
    @Transactional
    public void like(MemberDto memberDto) throws IOException {
        // 2. 이미 좋아요 된 그림인 경우 409 에러 호출하기
        if (findLikedPaintingWithMemberAndPaintingId(memberDto).isPresent()) {
            throw new PaintingException(PaintingExceptionType.ALREADY_LIKED_PAINTING);
        }

        // 3. 좋아요 한 그림 ID와 회원 ID로 DB에 저장하기
        Painting painting = paintingRepository.findById(memberDto.getPaintingId())
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        LikedPainting likedPainting = LikedPainting.builder()
                .painting(painting)
                .memberId(memberDto.getMemberId())
                .build();

        likedPainting.update(memberDto.getMemberId());
        likedPaintingRepository.save(likedPainting);

//        recommendRequestV2(likedPainting.getPainting().getId());

        // 4. 그림의 총 좋아요 수 증가
        updateLikeCount(memberDto.getPaintingId(), 1);
    }

    @Override
    @Transactional
    public void cancelLike(MemberDto memberDto) throws IOException {
        // 2. 좋아요 된 그림이 아닌 경우 409 에러 호출하기
        LikedPainting likedPainting
                = findLikedPaintingWithMemberAndPaintingId(memberDto)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_LIKED_PAINTING));

        // 3. DB 에서 좋아요 된 그림 객체 제거
        likedPaintingRepository.delete(likedPainting);

        // 4. 그림의 총 좋아요 수 감소
        updateLikeCount(memberDto.getPaintingId(), -1);
    }

    @Override
    public List<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable) {
        Page<LikedPainting> list = likedPaintingRepository.findLikedPaintings(memberId, pageable);

        List<LikedPaintingDto> result = list.stream()
                .map(painting -> new LikedPaintingDto(painting.getPainting()))
                .collect(Collectors.toList());

        return result;
    }

    // 회원 ID와 그림 ID로 좋아요 여부 확인
    public Optional<LikedPainting> findLikedPaintingWithMemberAndPaintingId(MemberDto memberDto) {
        return likedPaintingRepository
                .findByMemberIdAndPaintingId(memberDto.getMemberId(), memberDto.getPaintingId());
    }

    public void updateLikeCount(Long paintingId, Integer count) throws IOException {
        Painting findPainting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        findPainting.updateTotalLikeCount(count);
    }

    public void recommendRequestV1(Long paintingId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            URI uri = UriComponentsBuilder
                    .fromUriString("http://127.0.0.1:8000")
                    .path("/api/v1/paintings/like_recommend_painting/{painting_id}")
                    .build()
                    .expand(paintingId)
                    .toUri();

            ResponseEntity<Object> exchange = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
            System.out.println(exchange.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PaintingDto recommendRequestV2(Long paintingId) {
        PaintingDto result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("http://127.0.0.1:8000/api/v1/paintings/like_recommend_painting/{paintingId}")
                        .build(paintingId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PaintingDto.class)
                .block();

        System.out.println(result);
        return result;
    }
}
