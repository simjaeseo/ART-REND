package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailPageDto {
    private Long paintingId;
    private String artist;
    private String description;
    private String genre;
    private Integer hits;
    private String medium;
    private String title;
    private Integer totalLikeCount;
    private String url;
    private String year;

    public DetailPageDto(DetailRecommendedPainting painting) {
        this.paintingId = painting.getRecommended().getId();
        this.artist = painting.getRecommended().getArtist();
        this.description = painting.getRecommended().getDescription();
        this.genre = painting.getRecommended().getGenre();
        this.hits = painting.getRecommended().getHits();
        this.medium = painting.getRecommended().getMedium();
        this.title = painting.getRecommended().getTitle();
        this.totalLikeCount = painting.getRecommended().getTotalLikeCount();
        this.url = painting.getRecommended().getUrl();
        this.year = painting.getRecommended().getYear();
    }
}
