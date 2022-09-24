package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.service.SelectedPaintingService;
import com.artrend.businessservice.domain.painting.dto.SelectedPaintingDto;

import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/select")
@Slf4j
public class SelectedPaintingController {
    private final SelectedPaintingService selectedPaintingService;

    @Operation(summary = "회원 가입시 선택 될 그림 10개 조회하기", description = "닉네임 설정 후 그림 선택페이지에 있을" +
            "그림 10개를 조회하는 요청입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/list")
    public ResponseEntity<? extends DataResponse> findPaintings() {
        List<PaintingDto> paintings = selectedPaintingService.findPaintings();
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(paintings));
    }

    @Operation(summary = "회원 가입시 2개의 그림 선택하기", description = "닉네임 설정 후 그림 선택 페이지에서 " +
            "2개의 그림을 선택해 보내는 요청입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping
    public ResponseEntity<? extends DataResponse> selectPaintings(
            @RequestBody SelectedPaintingDto request) {
        if (request.getPaintings().size() > 2) {
            throw new PaintingException(PaintingExceptionType.TOO_MANY_ARGUMENTS);
        }
        SelectedPaintingDto selectedPaintingDto
                = selectedPaintingService.selectPaintings(request);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(selectedPaintingDto));
    }

    @Operation(summary = "회원 가입시 선택한 2개의 그림 정보를 받습니다.", description = "회원 가입시 선택한 2개의 그림 " +
            "정보를 받는 요청입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{memberId}")
    public ResponseEntity<? extends DataResponse> findSelectedPaintings(
            @PathVariable("memberId") Long memberId) {
        List<PaintingDto> selectedPaintings = selectedPaintingService.findSelectedPaintings(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(selectedPaintings));
    }

}
