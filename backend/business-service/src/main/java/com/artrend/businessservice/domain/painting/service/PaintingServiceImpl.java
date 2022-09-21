package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;

    @Override
    public PaintingDto findPainting(Long id) {
        Painting findPainting = paintingRepository.findById(id)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        return new PaintingDto(findPainting);
    }

    @Override
    public List<PaintingDto> findAllPaintings() {
        List<Painting> findPaintings = paintingRepository.findAll();
        List<PaintingDto> result = findPaintings.stream()
                .map(painting -> new PaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }


}
