package com.artrend.businessservice.domain.painting.service;


import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import com.artrend.businessservice.domain.painting.entity.SelectedPainting;
import com.artrend.businessservice.domain.painting.repository.FavoriteStyleRepository;
import com.artrend.businessservice.domain.painting.repository.SelectedPaintingRepository;
import com.artrend.businessservice.domain.painting.util.TokenValidate;
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
    private final TokenValidate tokenValidate;

    @Override
    @Transactional
    public SelectedPaintingDto selectPaintings(SelectedPaintingDto selectedPaintingDto, String token) {
        token = token.split(" ")[1].trim();
        tokenValidate.validateToken(selectedPaintingDto.getMemberId(), token);

        List<FavoriteStyle> list = favoriteStyleRepository.findAll();

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
    public List<PaintingDto> findSelectedPaintings(Long memberId, String token) {
        token = token.split(" ")[1].trim();
        tokenValidate.validateToken(memberId, token);

        List<SelectedPainting> findPaintings = selectedPaintingRepository.findAllByMemberId(memberId);

        List<PaintingDto> result = findPaintings.stream()
                .map(painting -> new PaintingDto(painting.getFavoriteStyle()))
                .collect(Collectors.toList());

        return result;
    }
}
