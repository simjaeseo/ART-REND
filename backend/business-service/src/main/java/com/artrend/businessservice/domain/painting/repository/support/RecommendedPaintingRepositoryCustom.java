package com.artrend.businessservice.domain.painting.repository.support;

import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendedPaintingRepositoryCustom {
    Page<RecommendedPainting> findRecommendedPaintings(Long memberId, Pageable pageable);
}
