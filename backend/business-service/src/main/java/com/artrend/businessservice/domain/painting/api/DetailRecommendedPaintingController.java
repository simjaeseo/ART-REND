package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.service.DetailRecommendedPaintingService;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/detail")
@Slf4j
public class DetailRecommendedPaintingController {
    private final DetailRecommendedPaintingService detailRecommendedPaintingService;

    @Operation(summary = "상세 페이지 추천된 그림 조회", description = "해당 그림과 연관된 추천 그림들의 정보를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{painting-id}")
    public ResponseEntity<? extends DataResponse> findDetailRecommendedPaintings(
            @PathVariable("painting-id") Long id, Pageable pageable) {
        List<PaintingDto> detailRecommendedPaintings =
                detailRecommendedPaintingService.findDetailRecommendedPaintings(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(detailRecommendedPaintings));
    }

//    @Operation(summary = "추천 페이지 그림 추천 받기", description = "영화 상세페이지의 추천 그림들이 저장됩니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "성공"),
//            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
//            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
//            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
//    })
//    @GetMapping
//    public ResponseEntity<? extends MessageResponse> getDetailPaintings() {
//        detailRecommendedPaintingService.recommendDetailPaintings();
//        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
//    }

}
