package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.Painting;
import lombok.Data;

import javax.persistence.Column;

@Data
public class PaintingResponse {
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
    private Long hits;
    private Long totalLikeCount;
    private Long totalChangeCount;

    public PaintingResponse(Painting painting) {
        this.id = painting.getId();
        this.title = painting.getTitle();
        this.koreanTitle = painting.getKoreanTitle();
        this.artTrend = painting.getArtTrend();
        this.artist = painting.getArtist();
        this.description = painting.getDescription();
        this.genre = painting.getGenre();
        this.year = painting.getYear();
        this.genre = painting.getGenre();
        this.dimensions = painting.getDimensions();
        this.medium = painting.getMedium();
        this.place = painting.getPlace();
        this.hits = painting.getHits();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.totalChangeCount = painting.getTotalChangeCount();
    }
}
