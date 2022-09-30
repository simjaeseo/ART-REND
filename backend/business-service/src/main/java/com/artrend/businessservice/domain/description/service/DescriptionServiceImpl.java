package com.artrend.businessservice.domain.description.service;

import com.artrend.businessservice.domain.description.dto.ArtTrendDto;
import com.artrend.businessservice.domain.description.dto.ArtTrendDto.ArtTrendListDto;
import com.artrend.businessservice.domain.description.dto.ArtistDto;
import com.artrend.businessservice.domain.description.dto.ArtistDto.ArtistListDto;
import com.artrend.businessservice.domain.description.dto.GenreDto;
import com.artrend.businessservice.domain.description.dto.GenreDto.GenreListDto;
import com.artrend.businessservice.domain.description.entity.ArtTrend;
import com.artrend.businessservice.domain.description.entity.Artist;
import com.artrend.businessservice.domain.description.entity.Genre;
import com.artrend.businessservice.domain.description.repository.ArtTrendRepository;
import com.artrend.businessservice.domain.description.repository.ArtistRepository;
import com.artrend.businessservice.domain.description.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DescriptionServiceImpl implements DescriptionService {
    private final ArtistRepository artistRepository;
    private final ArtTrendRepository artTrendRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<ArtistListDto> findArtistList(Pageable pageable) {
        Page<Artist> list = artistRepository.searchArtistList(pageable);

        List<ArtistListDto> result = list.stream()
                .map(artist -> new ArtistListDto(artist))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<ArtTrendListDto> findArtTrendList(Pageable pageable) {
        Page<ArtTrend> list = artTrendRepository.searchArtTrendList(pageable);

        List<ArtTrendListDto> result = list.stream()
                .map(artTrend -> new ArtTrendListDto(artTrend))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<GenreListDto> findGenreList(Pageable pageable) {
        Page<Genre> list = genreRepository.searchGenreList(pageable);

        List<GenreListDto> result = list.stream()
                .map(genre -> new GenreListDto(genre))
                .collect(Collectors.toList());

        return result;
    }
}
