package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.RecommendedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RecommendedPaintingServiceImpl implements RecommendedPaintingService {
    private final RecommendedPaintingRepository recommendedPaintingRepository;

    @Override
    public List<PaintingDto> findRecommendedPaintings(Long memberId, Pageable pageable) {
        Page<RecommendedPainting> list = recommendedPaintingRepository.findRecommendedPaintings(memberId, pageable);

        List<PaintingDto> result = list.stream()
                .map(painting -> new PaintingDto(painting.getPainting()))
                .collect(Collectors.toList());

        return result;
    }
}
