package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingResponseDto;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;

    public List<PaintingResponseDto> selectAllPaintings() {
        List<Painting> findPaintings = paintingRepository.findAll();
        List<PaintingResponseDto> result = findPaintings.stream()
                .map(PaintingResponseDto::new)
                .collect(Collectors.toList());

        return result;
    }
}
