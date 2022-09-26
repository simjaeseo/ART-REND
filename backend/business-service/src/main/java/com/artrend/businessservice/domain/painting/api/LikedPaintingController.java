package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.service.LikedPaintingService;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<? extends DataResponse> like(
            @RequestBody @Valid MemberDto memberDto) throws IOException {
        likedPaintingService.like(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse(memberDto));
    }

    @Operation(summary = "그림 좋아요 취소", description = "현재 접속한 회원 정보로 해당 그림을 좋아요 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @DeleteMapping
    public ResponseEntity<? extends DataResponse> cancelLike(
            @RequestBody @Valid MemberDto memberDto) throws IOException {
        likedPaintingService.cancelLike(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(memberDto));
    }

    @Operation(summary = "좋아요한 그림 조회", description = "회원 정보로 좋아요한 그림 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(description = "parameter - memberId : 조회할 회원 ID값\n" +
                    "parameter - size : 한 번에 조회할 그림의 개수\n" +
                    "parameter - page : 조회할 페이지(시작 인덱스 : 0)")
    })
    @GetMapping
    public ResponseEntity<? extends DataResponse> findLikedPaintings(Long memberId, Pageable pageable) {
        List<LikedPaintingDto> likedPaintings = likedPaintingService.findLikedPaintings(memberId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(likedPaintings));
    }
}
