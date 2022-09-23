package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import com.artrend.businessservice.domain.painting.entity.Painting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String description;
    private String genre;
    private String year;
    private String dimensions;
    private String medium;
    private String place;
    private Long hits;
    private Long totalLikeCount;
    private Long totalChangeCount;

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
        this.hits = painting.getHits();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.totalChangeCount = painting.getTotalChangeCount();
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
        this.hits = favoriteStyle.getPainting().getHits();
        this.totalLikeCount = favoriteStyle.getPainting().getTotalLikeCount();
        this.totalChangeCount = favoriteStyle.getPainting().getTotalChangeCount();
    }
}
