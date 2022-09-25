package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendedPaintingRepository extends JpaRepository<RecommendedPainting, Long>, RecommendedPaintingRepositoryCustom {

}
