package com.artrend.businessservice.domain.description.api;

import com.artrend.businessservice.domain.description.dto.ArtTrendDto.ArtTrendListDto;
import com.artrend.businessservice.domain.description.dto.ArtistDto.ArtistListDto;
import com.artrend.businessservice.domain.description.dto.GenreDto.GenreListDto;
import com.artrend.businessservice.domain.description.service.DescriptionService;
import com.artrend.businessservice.global.common.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/description")
@Slf4j
public class DescriptionController {
    private final DescriptionService descriptionService;

    @Operation(summary = "화가 목록 조회", description = "화가 리스트를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/artist")
    public ResponseEntity<? extends DataResponse> searchArtistList(Pageable pageable) {
        List<ArtistListDto> response = descriptionService.findArtistList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @Operation(summary = "예술 사조 목록 조회", description = "예술 사조 리스트를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/art-trend")
    public ResponseEntity<? extends DataResponse> searchArtTrendList(Pageable pageable) {
        List<ArtTrendListDto> response = descriptionService.findArtTrendList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }

    @Operation(summary = "그림 장르 조회", description = "그림 장르 리스트를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/genre")
    public ResponseEntity<? extends DataResponse> searchGenreList(Pageable pageable) {
        List<GenreListDto> response = descriptionService.findGenreList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(response));
    }
}
