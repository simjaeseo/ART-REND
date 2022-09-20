package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingResponse;

import java.util.List;

public interface PaintingService {
    PaintingResponse findPainting(Long id);
    List<PaintingResponse> findAllPaintings();
}
