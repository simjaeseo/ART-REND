package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.service.PaintingService;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paintings")
@Slf4j
public class PaintingController {
    private final PaintingService paintingService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check(){
        log.info("Server port={}", env.getProperty("local.server.port"));

        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    @Operation(summary = "그림 전체 조회", description = "등록된 모든 그림의 데이터를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping
    public ResponseEntity<? extends DataResponse> findAllPaintings() {
        List<PaintingDto> paintingList = paintingService.findAllPaintings();

        return ResponseEntity.status(HttpStatus.OK).body(new CountDataResponse(paintingList, paintingList.size()));
    }

    @Operation(summary = "그림 전체 조회", description = "등록된 모든 그림의 데이터를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{painting_id}")
    public ResponseEntity<? extends DataResponse> findPainting(@PathVariable("painting_id") Long id) {
        PaintingDto findPainting = paintingService.findPainting(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(findPainting));
    }
}
