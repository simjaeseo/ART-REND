package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendedDataDto;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import com.artrend.businessservice.domain.painting.vo.SearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaintingService {
    RecommendedDataDto findPainting(Long paintingId, Long memberId, Pageable pageable);
    List<PaintingDto> findAllPaintings();
    SearchResponse searchPaintings(SearchCondition condition, Pageable pageable);
    List<PaintingDto> sortPaintings(PaintingCondition condition, Pageable pageable);
}
