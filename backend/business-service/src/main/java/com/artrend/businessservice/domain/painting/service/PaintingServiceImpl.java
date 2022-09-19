package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;

    public Iterable<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }
}
