package com.artrend.businessservice.domain.painting.util;

import com.artrend.businessservice.domain.painting.service.DetailRecommendedPaintingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunAfterApplicationStart implements ApplicationRunner {
    private final DetailRecommendedPaintingService detailRecommendedPaintingService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        detailRecommendedPaintingService.recommendDetailPaintings();
    }
}
