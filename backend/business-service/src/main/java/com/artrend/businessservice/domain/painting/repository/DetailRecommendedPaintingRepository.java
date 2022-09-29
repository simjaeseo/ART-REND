package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.support.DetailRecommendedPaintingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRecommendedPaintingRepository extends JpaRepository<DetailRecommendedPainting, Long>, DetailRecommendedPaintingRepositoryCustom {
}
