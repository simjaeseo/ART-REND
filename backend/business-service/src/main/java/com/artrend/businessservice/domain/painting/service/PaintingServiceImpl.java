package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.RecommendDto;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final static String BASE_URL = "http://127.0.0.1:8000/api/v1/paintings/";
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;
    private final LikedPaintingService likedPaintingService;

    @Override
    @Transactional
    public RecommendDto findPainting(Long paintingId, Long memberId) {
        Painting findPainting = paintingRepository
                .findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        if (findPainting == null) {
            throw new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING);
        }

        Optional<LikedPainting> likedPainting =
                likedPaintingRepository.findByMemberIdAndPaintingId(memberId, paintingId);

        Boolean isLiked = true;
        if (likedPainting.isEmpty()) {
            isLiked = false;
        }

        findPainting.updateHits();

        ResponseEntity<Object> objectResponseEntity = null;

        if (isLiked == true) {
            objectResponseEntity = likedPaintingService.recommendRequestV2(likedPainting.get().getId());
            return new RecommendDto(new PaintingDto(findPainting, true), objectResponseEntity);
        }

        return new RecommendDto(new PaintingDto(findPainting, false));
    }

    @Override
    public List<PaintingDto> findAllPaintings() {
        List<Painting> findPaintings = paintingRepository.findAll();

        if (findPaintings.isEmpty()) {
            throw new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING);
        }

        List<PaintingDto> result = findPaintings.stream()
                .map(painting -> new PaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<PaintingDto> searchPaintings(SearchCondition condition, Pageable pageable) {
        Page<Painting> list = paintingRepository.searchPaintings(condition, pageable);

        List<PaintingDto> result = list.stream()
                .map(painting -> new PaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<PaintingDto> sortPaintings(PaintingCondition condition, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(0, 20,
                Sort.by(Sort.Direction.DESC, condition.getType()));

        Page<Painting> list = paintingRepository.sortPaintings(condition, pageRequest);

        List<PaintingDto> result = list.stream()
                .map(painting -> new PaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void getMainPaintings(HttpHeaders requestHeader) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        restTemplate.exchange("http://127.0.0.1:8000/api/v1/paintings/main_recommend_painting/", HttpMethod.POST, entity, Object.class);
    }
}
