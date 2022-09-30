package com.artrend.businessservice.domain.painting.repository.custom;

import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DetailRecommendedPaintingRepositoryCustom {
    Page<DetailRecommendedPainting> findDetailRecommendedPaintings(Long paintingId, Pageable pageable);
}
