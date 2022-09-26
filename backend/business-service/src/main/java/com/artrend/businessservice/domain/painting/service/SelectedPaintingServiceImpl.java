package com.artrend.businessservice.domain.painting.service;


import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import com.artrend.businessservice.domain.painting.entity.SelectedPainting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.FavoriteStyleRepository;
import com.artrend.businessservice.domain.painting.repository.SelectedPaintingRepository;
import com.artrend.businessservice.domain.painting.dto.SelectedPaintingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SelectedPaintingServiceImpl implements SelectedPaintingService {
    private final SelectedPaintingRepository selectedPaintingRepository;
    private final FavoriteStyleRepository favoriteStyleRepository;

    @Override
    public List<PaintingDto> findPaintings() {
        List<FavoriteStyle> list = favoriteStyleRepository.findAll();

        if (list.isEmpty()) {
            throw new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING);
        }

        List<PaintingDto> result = list.stream()
                .map(favoriteStyle-> new PaintingDto(favoriteStyle))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public SelectedPaintingDto selectPaintings(SelectedPaintingDto selectedPaintingDto) {
        List<FavoriteStyle> list = favoriteStyleRepository.findAll();

        if (list.isEmpty()) {
            throw new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING);
        }

        FavoriteStyle firstFavorite = null;
        FavoriteStyle secondFavorite = null;

        for (FavoriteStyle favoriteStyle : list) {
            if (favoriteStyle.getId() == selectedPaintingDto.getPaintings().get(0)) {
                firstFavorite = favoriteStyle;
            } else if (favoriteStyle.getId() == selectedPaintingDto.getPaintings().get(1)) {
                secondFavorite = favoriteStyle;
            }

            if (firstFavorite != null && secondFavorite != null) {
                break;
            }
        }

        SelectedPainting firstSelectedPainting = SelectedPainting.builder()
                .memberId(selectedPaintingDto.getMemberId())
                .favoriteStyle(firstFavorite)
                .build();

        SelectedPainting secondSelectedPainting = SelectedPainting.builder()
                .memberId(selectedPaintingDto.getMemberId())
                .favoriteStyle(secondFavorite)
                .build();

        selectedPaintingRepository.save(firstSelectedPainting);
        selectedPaintingRepository.save(secondSelectedPainting);

        return selectedPaintingDto;
    }

    @Override
    public List<PaintingDto> findSelectedPaintings(Long memberId) {
        List<SelectedPainting> findPaintings = selectedPaintingRepository.findAllByMemberId(memberId);

        List<PaintingDto> result = findPaintings.stream()
                .map(painting -> new PaintingDto(painting.getFavoriteStyle()))
                .collect(Collectors.toList());

        return result;
    }
}
