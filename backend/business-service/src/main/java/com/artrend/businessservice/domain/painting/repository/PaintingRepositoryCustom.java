package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;

import com.artrend.businessservice.domain.painting.entity.Painting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaintingRepositoryCustom {
    Page<Painting> searchPaintings(SearchCondition condition, Pageable pageable);
    Page<Painting> sortPaintings(PaintingCondition condition, Pageable pageable);
}
