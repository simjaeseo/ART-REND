package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.SelectedPaintingDto;

import java.util.List;

public interface SelectedPaintingService {
    SelectedPaintingDto selectPaintings(SelectedPaintingDto selectedPaintingDto, String token);

    List<PaintingDto> findSelectedPaintings(Long memberId, String token);
}
