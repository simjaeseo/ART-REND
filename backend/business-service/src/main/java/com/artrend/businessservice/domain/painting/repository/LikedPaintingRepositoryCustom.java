package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LikedPaintingRepositoryCustom {
    Page<LikedPainting> findLikedPaintings(Long memberId, Pageable pageable);
}
