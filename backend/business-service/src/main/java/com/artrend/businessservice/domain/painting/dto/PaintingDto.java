package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaintingDto {
    private Long id;
    private String title;
    private String koreanTitle;
    private String artTrend;
    private String artist;
    @Column(length = 5000)
    private String description;
    private String genre;
    private String year;
    private String dimensions;
    private String medium;
    private String place;
    private String url;
    private Integer hits;
    private Integer totalLikeCount;
    private Integer totalChangeCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isLiked;

    @QueryProjection
    public PaintingDto(Painting painting) {
        this.id = painting.getId();
        this.title = painting.getTitle();
        this.koreanTitle = painting.getKoreanTitle();
        this.artTrend = painting.getArtTrend();
        this.artist = painting.getArtist();
        this.description = painting.getDescription();
        this.genre = painting.getGenre();
        this.year = painting.getYear();
        this.dimensions = painting.getDimensions();
        this.medium = painting.getMedium();
        this.place = painting.getPlace();
        this.url = painting.getUrl();
        this.hits = painting.getHits();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.totalChangeCount = painting.getTotalChangeCount();
    }

    public PaintingDto(Painting painting, Boolean isLiked) {
        this.id = painting.getId();
        this.title = painting.getTitle();
        this.koreanTitle = painting.getKoreanTitle();
        this.artTrend = painting.getArtTrend();
        this.artist = painting.getArtist();
        this.description = painting.getDescription();
        this.genre = painting.getGenre();
        this.year = painting.getYear();
        this.dimensions = painting.getDimensions();
        this.medium = painting.getMedium();
        this.place = painting.getPlace();
        this.url = painting.getUrl();
        this.hits = painting.getHits();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.totalChangeCount = painting.getTotalChangeCount();
        this.isLiked = isLiked;
    }

    public PaintingDto(FavoriteStyle favoriteStyle) {
        this.id = favoriteStyle.getPainting().getId();
        this.title = favoriteStyle.getPainting().getTitle();
        this.koreanTitle = favoriteStyle.getPainting().getKoreanTitle();
        this.artTrend = favoriteStyle.getPainting().getArtTrend();
        this.artist = favoriteStyle.getPainting().getArtist();
        this.description = favoriteStyle.getPainting().getDescription();
        this.genre = favoriteStyle.getPainting().getGenre();
        this.year = favoriteStyle.getPainting().getYear();
        this.dimensions = favoriteStyle.getPainting().getDimensions();
        this.medium = favoriteStyle.getPainting().getMedium();
        this.place = favoriteStyle.getPainting().getPlace();
        this.url = favoriteStyle.getPainting().getUrl();
        this.hits = favoriteStyle.getPainting().getHits();
        this.totalLikeCount = favoriteStyle.getPainting().getTotalLikeCount();
        this.totalChangeCount = favoriteStyle.getPainting().getTotalChangeCount();
    }

    @QueryProjection
    public PaintingDto(DetailRecommendedPainting detailRecommendedPainting) {
        this.id = detailRecommendedPainting.getRecommended().getId();
        this.title = detailRecommendedPainting.getRecommended().getTitle();
        this.koreanTitle = detailRecommendedPainting.getRecommended().getKoreanTitle();
        this.artTrend = detailRecommendedPainting.getRecommended().getArtTrend();
        this.artist = detailRecommendedPainting.getRecommended().getArtist();
        this.description = detailRecommendedPainting.getRecommended().getDescription();
        this.genre = detailRecommendedPainting.getRecommended().getGenre();
        this.year = detailRecommendedPainting.getRecommended().getYear();
        this.dimensions = detailRecommendedPainting.getRecommended().getDimensions();
        this.medium = detailRecommendedPainting.getRecommended().getMedium();
        this.place = detailRecommendedPainting.getRecommended().getPlace();
        this.url = detailRecommendedPainting.getRecommended().getUrl();
        this.hits = detailRecommendedPainting.getRecommended().getHits();
        this.totalLikeCount = detailRecommendedPainting.getRecommended().getTotalLikeCount();
        this.totalChangeCount = detailRecommendedPainting.getRecommended().getTotalChangeCount();
    }
}
