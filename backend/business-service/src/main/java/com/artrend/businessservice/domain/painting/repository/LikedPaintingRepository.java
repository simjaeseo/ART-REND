package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPaintingRepository extends JpaRepository<LikedPainting, Long> {
}
