package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.service.PaintingService;
import com.artrend.businessservice.domain.painting.dto.PaintingResponseDto;
import com.artrend.businessservice.global.common.CountDataResponse;
import com.artrend.businessservice.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paintings")
@Slf4j
public class PaintingController {
    private final PaintingService paintingService;
    private final Environment env;

    @GetMapping("/health_check")
    public String check(){
        log.info("Server port={}", env.getProperty("local.server.port"));

        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    @GetMapping
    public ResponseEntity<? extends DataResponse> getAllPaintings() {
        List<PaintingResponseDto> paintingList = paintingService.selectAllPaintings();

        return ResponseEntity.status(HttpStatus.OK).body(new CountDataResponse(paintingList, paintingList.size()));
    }
}
