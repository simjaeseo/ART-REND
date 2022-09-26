package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaintingService {
    PaintingDto findPainting(Long paintingId, Long memberId);
    List<PaintingDto> findAllPaintings();
    List<PaintingDto> searchPaintings(SearchCondition condition, Pageable pageable);
    List<PaintingDto> sortPaintings(PaintingCondition condition, Pageable pageable);
}
