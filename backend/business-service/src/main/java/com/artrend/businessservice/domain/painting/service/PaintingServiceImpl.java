package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikeDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.LikedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PaintingServiceImpl implements PaintingService {
    private final PaintingRepository paintingRepository;
    private final LikedPaintingRepository likedPaintingRepository;

    @Override
    @Transactional
    public PaintingDto findPainting(Long paintingId, Long memberId) {
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
        return new PaintingDto(findPainting, isLiked);
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


}
