package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.service.LikedPaintingService;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
@Slf4j
public class LikedPaintingController {
    private final LikedPaintingService likedPaintingService;

    @Operation(summary = "그림 좋아요", description = "현재 접속한 회원 정보로 해당 그림을 좋아요합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping
    public ResponseEntity<? extends DataResponse> like(@RequestBody @Valid
                                                           LikedPaintingDto likedPaintingDto) throws IOException {
        likedPaintingService.like(likedPaintingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse(likedPaintingDto));
    }

    @Operation(summary = "그림 좋아요 취소", description = "현재 접속한 회원 정보로 해당 그림을 좋아요 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @DeleteMapping
    public ResponseEntity<? extends DataResponse> cancelLike(@RequestBody @Valid
                                                             LikedPaintingDto likedPaintingDto) throws IOException {
        likedPaintingService.cancelLike(likedPaintingDto);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(likedPaintingDto));
    }
}
