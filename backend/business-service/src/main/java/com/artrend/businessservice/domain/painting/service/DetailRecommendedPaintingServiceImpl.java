package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.repository.DetailRecommendedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DetailRecommendedPaintingServiceImpl implements DetailRecommendedPaintingService {
    private final DetailRecommendedPaintingRepository detailRecommendedPaintingRepository;
    private final PaintingRepository paintingRepository;

    @Override
    public List<PaintingDto> findDetailRecommendedPaintings(Long id) {

        return null;
    }
}
