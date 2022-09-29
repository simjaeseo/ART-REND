package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.repository.support.LikedPaintingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LikedPaintingRepository extends JpaRepository<LikedPainting, Long>, LikedPaintingRepositoryCustom {
    Optional<LikedPainting> findByMemberIdAndPaintingId(Long memberId, Long paintingId);
}
