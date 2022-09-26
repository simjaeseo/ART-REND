package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.DetailRecommendedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DetailRecommendedPaintingServiceImpl implements DetailRecommendedPaintingService {
    private final DetailRecommendedPaintingRepository detailRecommendedPaintingRepository;

    @Override
    public List<PaintingDto> findDetailRecommendedPaintings(Long id, Pageable pageable) {
        Page<DetailRecommendedPainting> list = detailRecommendedPaintingRepository.findDetailRecommendedPaintings(id, pageable);

        List<PaintingDto> result = list.stream()
                .map(painting -> new PaintingDto(painting.getRecommended()))
                .collect(Collectors.toList());

        return result;
    }
}
