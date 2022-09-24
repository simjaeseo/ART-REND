package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikeDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;

import java.util.List;

public interface PaintingService {
    PaintingDto findPainting(Long paintingId, Long memberId);
    List<PaintingDto> findAllPaintings();
}
