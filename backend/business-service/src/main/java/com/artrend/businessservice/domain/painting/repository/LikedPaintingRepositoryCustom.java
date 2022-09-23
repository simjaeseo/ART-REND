package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LikedPaintingRepositoryCustom {
    Page<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable);
}
