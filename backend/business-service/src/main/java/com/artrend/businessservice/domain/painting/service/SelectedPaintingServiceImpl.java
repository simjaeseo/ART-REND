package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.repository.SelectedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SelectedPaintingServiceImpl implements SelectedPaintingService {
    private final SelectedPaintingRepository selectedPaintingRepository;
}
