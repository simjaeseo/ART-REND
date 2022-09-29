package com.artrend.businessservice.domain.description.service;

import com.artrend.businessservice.domain.description.dto.ArtTrendDto.ArtTrendListDto;
import com.artrend.businessservice.domain.description.dto.ArtistDto.ArtistListDto;
import com.artrend.businessservice.domain.description.dto.GenreDto.GenreListDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DescriptionService {
    List<ArtistListDto> findArtistList(Pageable pageable);
    List<ArtTrendListDto> findArtTrendList(Pageable pageable);
    List<GenreListDto> findGenreList(Pageable pageable);
}
