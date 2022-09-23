package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.service.DetailRecommendedPaintingService;
import com.artrend.businessservice.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/detail")
@Slf4j
public class DetailRecommendedPaintingController {
    private final DetailRecommendedPaintingService detailRecommendedPaintingService;

    @GetMapping("/{painting-id}")
    public ResponseEntity<? extends DataResponse> findDetailRecommendedPaintings(@PathVariable("painting-id") Long id) {
        detailRecommendedPaintingService.findDetailRecommendedPaintings(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(0));
    }
}
