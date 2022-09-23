package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;

import java.util.List;

public interface DetailRecommendedPaintingService {
    List<PaintingDto> findDetailRecommendedPaintings(Long id);
}
