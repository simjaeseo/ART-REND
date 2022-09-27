package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.SelectedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectedPaintingRepository extends JpaRepository<SelectedPainting, Long> {

    List<SelectedPainting> findAllByMemberId(Long memberId);
}
