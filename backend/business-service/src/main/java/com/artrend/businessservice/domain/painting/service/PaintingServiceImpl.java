package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingResponse;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;

    public PaintingResponse findPainting(Long id) {
        Painting findPainting = paintingRepository.findById(id)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        return new PaintingResponse(findPainting);
    }

    public List<PaintingResponse> findAllPaintings() {
        List<Painting> findPaintings = paintingRepository.findAll();
        List<PaintingResponse> result = findPaintings.stream()
                .map(PaintingResponse::new)
                .collect(Collectors.toList());

        return result;
    }
}
