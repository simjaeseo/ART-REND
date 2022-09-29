package com.artrend.businessservice.domain.painting.vo;

import com.artrend.businessservice.domain.description.dto.ArtTrendDto;
import com.artrend.businessservice.domain.description.dto.ArtistDto;
import com.artrend.businessservice.domain.description.dto.GenreDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse {
    private List<PaintingDto> paintings;
    private ArtistDto artist;
    private GenreDto genre;
    private ArtTrendDto artTrend;

    public SearchResponse(List<PaintingDto> paintings) {
        this.paintings = paintings;
    }

    public SearchResponse(List<PaintingDto> paintings, ArtistDto artist) {
        this.paintings = paintings;
        this.artist = artist;
    }

    public SearchResponse(List<PaintingDto> paintings, GenreDto genre) {
        this.paintings = paintings;
        this.genre = genre;
    }

    public SearchResponse(List<PaintingDto> paintings, ArtTrendDto artTrend) {
        this.paintings = paintings;
        this.artTrend = artTrend;
    }

}
