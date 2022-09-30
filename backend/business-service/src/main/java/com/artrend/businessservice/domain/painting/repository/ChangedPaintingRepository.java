package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.ChangedPainting;
import com.artrend.businessservice.domain.painting.repository.custom.ChangedPaintingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChangedPaintingRepository extends JpaRepository<ChangedPainting, Long>, ChangedPaintingRepositoryCustom {
    Optional<ChangedPainting> findByMemberIdAndPaintingId(Long memberId, Long paintingId);
}
