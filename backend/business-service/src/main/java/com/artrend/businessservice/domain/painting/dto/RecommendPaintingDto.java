package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
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

    public RecommendPaintingDto(RecommendedPainting recommendedPainting) {
        this.paintingId = recommendedPainting.getPainting().getId();
        this.artist = recommendedPainting.getPainting().getArtist();
        this.description = recommendedPainting.getPainting().getDescription();
        this.genre = recommendedPainting.getPainting().getGenre();
        this.medium = recommendedPainting.getPainting().getMedium();
        this.title = recommendedPainting.getPainting().getTitle();
        this.totalLikeCount = recommendedPainting.getPainting().getTotalLikeCount();
        this.url = recommendedPainting.getPainting().getUrl();
        this.year = recommendedPainting.getPainting().getYear();
    }
}
