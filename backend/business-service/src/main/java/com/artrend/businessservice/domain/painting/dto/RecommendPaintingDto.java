package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.Painting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendPaintingDto {
    private Long paintingId;
    private String artist;
    private String description;
    private String genre;
    private String medium;
    private String title;
    private Integer totalLikeCount;
    private String url;
    private String year;

    public RecommendPaintingDto(Painting painting) {
        this.paintingId = painting.getId();
        this.artist = painting.getArtist();
        this.description = painting.getDescription();
        this.genre = painting.getGenre();
        this.medium = painting.getMedium();
        this.title = painting.getTitle();
        this.totalLikeCount = painting.getTotalLikeCount();
        this.url = painting.getUrl();
        this.year = painting.getYear();
    }
}
