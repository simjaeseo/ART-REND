package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetailRecommendedPaintingRepositoryCustom {
    Page<PaintingDto> findDetailRecommendedPaintings(Long paintingId, Pageable pageable);
}
