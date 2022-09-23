package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.service.SelectedPaintingService;
import com.artrend.businessservice.domain.painting.dto.SelectedPaintingDto;

import com.artrend.businessservice.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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

    @PostMapping
    public ResponseEntity<? extends DataResponse> selectPaintings(
            @RequestBody SelectedPaintingDto request,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        if (request.getPaintings().size() > 2) {
            throw new PaintingException(PaintingExceptionType.TOO_MANY_ARGUMENTS);
        }
        SelectedPaintingDto selectedPaintingDto
                = selectedPaintingService.selectPaintings(request, token);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(selectedPaintingDto));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<? extends DataResponse> findSelectedPaintings(
            @PathVariable("memberId") Long memberId,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        List<PaintingDto> selectedPaintings = selectedPaintingService.findSelectedPaintings(memberId, token);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(selectedPaintings));
    }

}
