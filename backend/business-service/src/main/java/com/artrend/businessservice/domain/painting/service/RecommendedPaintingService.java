package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface RecommendedPaintingService {
    List<PaintingDto> findRecommendedPaintings(Long memberId, HttpHeaders headers, Pageable pageable);
    void recommendMainPaintings(String authorization);
}
