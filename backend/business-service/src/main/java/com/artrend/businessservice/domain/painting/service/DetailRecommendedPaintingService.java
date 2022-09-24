package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetailRecommendedPaintingService {
    Page<PaintingDto> findDetailRecommendedPaintings(Long id, Pageable pageable);
}
