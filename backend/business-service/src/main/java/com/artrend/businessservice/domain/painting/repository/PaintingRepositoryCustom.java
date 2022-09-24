package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaintingRepositoryCustom {
    Page<PaintingDto> searchPaintings(PaintingCondition condition, Pageable pageable);
}
