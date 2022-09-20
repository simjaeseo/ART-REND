package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingResponseDto;
import com.artrend.businessservice.domain.painting.entity.Painting;

import java.util.List;

public interface PaintingService {
    List<PaintingResponseDto> selectAllPaintings();
}
