package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.ChangedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangedPaintingRepository extends JpaRepository<ChangedPainting, Long> {
}
