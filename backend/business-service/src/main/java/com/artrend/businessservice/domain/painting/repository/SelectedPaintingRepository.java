package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.SelectedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedPaintingRepository extends JpaRepository<Long, SelectedPainting> {
}
