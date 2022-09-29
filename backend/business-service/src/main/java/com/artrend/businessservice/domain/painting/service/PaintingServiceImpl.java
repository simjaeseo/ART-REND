package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.*;
import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.DetailRecommendedPaintingRepository;
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
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;
    private final LikedPaintingService likedPaintingService;
    private final DetailRecommendedPaintingRepository detailRecommendedPaintingRepository;

    @Override
    @Transactional
    public RecommendDto findPainting(Long paintingId, Long memberId, Pageable pageable) {
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

        Page<DetailRecommendedPainting> list = detailRecommendedPaintingRepository
                .findDetailRecommendedPaintings(paintingId, pageable);

        List<DetailDto> result = list.stream()
                .map(painting -> new DetailDto(painting))
                .collect(Collectors.toList());

        return new RecommendDto(new PaintingDto(findPainting, false), result);
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
}
