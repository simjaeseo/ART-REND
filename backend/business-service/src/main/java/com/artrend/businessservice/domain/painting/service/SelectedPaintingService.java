package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.SelectedPaintingDto;

import java.util.List;

public interface SelectedPaintingService {
    List<PaintingDto> findPaintings();
    SelectedPaintingDto selectPaintings(SelectedPaintingDto selectedPaintingDto);
    List<PaintingDto> findSelectedPaintings(Long memberId);
}
