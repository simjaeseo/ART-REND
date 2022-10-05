package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.vo.DetailResponse;
import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface LikedPaintingService {
    PaintingDto like(MemberDto memberDto) throws IOException;
    DetailResponse cancelLike(MemberDto memberDto, Pageable pageable) throws IOException;
    List<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable);
    ResponseEntity<Object> recommendRequestV2(Long paintingId);
}
