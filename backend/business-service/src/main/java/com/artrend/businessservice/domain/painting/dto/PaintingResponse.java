package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.Painting;
import lombok.Data;

@Data
public class PaintingResponse {
    private Long id;
    private String title;
    private String artist;
    private String description;
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
        this.artist = painting.getArtist();
        this.description = painting.getDescription();
        this.year = painting.getYear();
        this.dimensions = painting.getDimensions();
        this.medium = painting.getMedium();
        this.place = painting.getPlace();
        this.hits = painting.getHits();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.totalChangeCount = painting.getTotalChangeCount();
    }
}
