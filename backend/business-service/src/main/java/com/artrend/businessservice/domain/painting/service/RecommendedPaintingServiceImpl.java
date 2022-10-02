package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendPaintingDto;
import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.RecommendedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RecommendedPaintingServiceImpl implements RecommendedPaintingService {
    private final RecommendedPaintingRepository recommendedPaintingRepository;

    @Override
    public List<RecommendPaintingDto> findRecommendedPaintings(Long memberId, String authorization, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", authorization);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        restTemplate.exchange("http://127.0.0.1:8000/api/v1/paintings/main_recommend_painting/", HttpMethod.POST, entity, Object.class);

        Page<RecommendedPainting> list = recommendedPaintingRepository.findRecommendedPaintings(memberId, pageable);

        List<RecommendPaintingDto> result = list.stream()
                .map(painting -> new RecommendPaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public ResponseEntity<Object> mainPageRecommend(String authorization) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Object> result
                = restTemplate.exchange(
                "http://127.0.0.1:8000/api/v1/paintings/main_page_recommend/",
                HttpMethod.GET,
                entity,
                Object.class
        );
//        URI uri = UriComponentsBuilder
//                .fromUriString("http://localhost:8000")
//                .path("/api/v1/paintings/main_page_recommend/")
//                .encode()
//                .build()
//                .toUri();

//        ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);

        return result;
    }
}
