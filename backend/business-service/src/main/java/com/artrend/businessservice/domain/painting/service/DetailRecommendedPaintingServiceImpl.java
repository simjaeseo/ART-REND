package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.DetailRecommendedPaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DetailRecommendedPaintingServiceImpl implements DetailRecommendedPaintingService {
    private final DetailRecommendedPaintingRepository detailRecommendedPaintingRepository;

    @Override
    public List<PaintingDto> findDetailRecommendedPaintings(Long id, Pageable pageable) {
        Page<DetailRecommendedPainting> list = detailRecommendedPaintingRepository.findDetailRecommendedPaintings(id, pageable);

        List<PaintingDto> result = list.stream()
                .map(painting -> new PaintingDto(painting.getRecommended()))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void recommendDetailPaintings() {
        List<DetailRecommendedPainting> list = detailRecommendedPaintingRepository.findAll();
        if (list.isEmpty()) {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            restTemplate.exchange("http://localhost:8000/api/v1/paintings/make_detail_recommend/", HttpMethod.POST, entity, Object.class);
        }
    }
}
