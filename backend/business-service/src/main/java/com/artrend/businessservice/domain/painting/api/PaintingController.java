package com.artrend.businessservice.domain.painting.api;

import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.service.PaintingService;
import com.artrend.businessservice.domain.painting.vo.ResponsePainting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/painting-service")
@Slf4j
public class PaintingController {
    private final PaintingService paintingService;
    private final Environment env;

    @GetMapping("/check")
    public String check(){
        log.info("Server port={}", env.getProperty("local.server.port"));

        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/paintings")
    public ResponseEntity<List<ResponsePainting>> getAllPaintings() {
        Iterable<Painting> paintingList = paintingService.getAllPaintings();

        List<ResponsePainting> result = new ArrayList<>();
        paintingList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponsePainting.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
