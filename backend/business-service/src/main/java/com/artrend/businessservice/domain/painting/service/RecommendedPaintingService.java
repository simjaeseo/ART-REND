package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecommendedPaintingService {
    List<PaintingDto> findRecommendedPaintings(Long memberId, Pageable pageable);
}
