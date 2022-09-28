package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendDto;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PaintingService {
    RecommendDto findPainting(Long paintingId, Long memberId);
    List<PaintingDto> findAllPaintings();
    List<PaintingDto> searchPaintings(SearchCondition condition, Pageable pageable);
    List<PaintingDto> sortPaintings(PaintingCondition condition, Pageable pageable);

    void getMainPaintings(HttpHeaders headers);
}
