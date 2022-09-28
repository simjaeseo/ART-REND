package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paintings")
@Slf4j
public class PaintingController {
    private final PaintingService paintingService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check() {
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

    @Operation(summary = "그림 개별 조회", description = "선택한 그림 ID의 모든 그림 정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{painting_id}/{member_id}")
    public ResponseEntity<? extends DataResponse> findPainting(@PathVariable("painting_id") Long paintingId,
                                                               @PathVariable("member_id") Long memberId) {
        PaintingDto findPainting = paintingService.findPainting(paintingId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(findPainting));
    }

    @Operation(summary = "그림 장르별 조회", description = "genre, artTrend, artist 별로 그림을 조회할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/search")
    public ResponseEntity<? extends DataResponse> searchPaintings(SearchCondition condition, Pageable pageable) {
        List<PaintingDto> paintings = paintingService.searchPaintings(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(paintings));
    }

    @Operation(summary = "그림 통계별 조회", description = "조회수, 좋아요수, 변환수 별로 그림을 조회할 수 있습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/sort")
    public ResponseEntity<? extends DataResponse> sortPaintings(PaintingCondition condition, Pageable pageable) {
        List<PaintingDto> paintings = paintingService.sortPaintings(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(paintings));
    }

    @GetMapping("/client")
    public void api() {
        try {
            AsyncRestTemplate restTemplate = new AsyncRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            Object response = restTemplate.exchange("http://127.0.0.1:8000/api/v1/paintings/make_detail_recommend/", HttpMethod.POST, entity, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
