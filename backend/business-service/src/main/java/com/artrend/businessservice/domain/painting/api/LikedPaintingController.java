package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.service.LikedPaintingService;
import com.artrend.businessservice.global.common.DataResponse;
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

    @PostMapping
    public ResponseEntity<? extends DataResponse> like(@RequestBody @Valid
                                                           LikedPaintingDto likedPaintingDto) throws IOException {
        likedPaintingService.like(likedPaintingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse(likedPaintingDto));
    }

    @DeleteMapping
    public ResponseEntity<? extends DataResponse> cancelLike(@RequestBody @Valid
                                                             LikedPaintingDto likedPaintingDto) throws IOException {
        likedPaintingService.cancelLike(likedPaintingDto);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(likedPaintingDto));
    }
}
