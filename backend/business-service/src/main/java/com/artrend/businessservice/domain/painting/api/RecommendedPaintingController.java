package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.service.RecommendedPaintingService;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import com.artrend.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
@Slf4j
public class RecommendedPaintingController {
    private final RecommendedPaintingService recommendedPaintingService;

    @Operation(summary = "메인 페이지 추천 그림 목록 조회", description = "해당 회원에게 메인 페이지에 보여질 " +
            "추천된 그림 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<? extends DataResponse> findRecommendedPaintings(@PathVariable("memberId")
                                                                               Long memberId,
                                                                           @RequestHeader HttpHeaders headers,
                                                                           Pageable pageable) {
        List<PaintingDto> recommendedPaintings =
                recommendedPaintingService.findRecommendedPaintings(memberId, headers, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CountDataResponse(recommendedPaintings, recommendedPaintings.size()));
    }

    @Operation(summary = "그림선택후 메인 페이지에 추천된 그림 리스트들을 갱신합니다.", description = "회원 가입시" +
            " 그림 선택한 결과에 따라 회원에게 맞는 그림 리스트들을 메인 페이지에 띄우는 용도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping
    public ResponseEntity<? extends MessageResponse> recommendMainPaintings(@RequestHeader(value = "Authorization") String authorization) {
        recommendedPaintingService.recommendMainPaintings(authorization);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse());
    }
}
