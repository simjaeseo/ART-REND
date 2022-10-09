package com.artrend.businessservice.domain.description.dto;

import com.artrend.businessservice.domain.description.entity.ArtTrend;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtTrendDto {
    private String name;
    private String description;
    private String url;

    @Data
    @NoArgsConstructor
    public static class ArtTrendListDto {
        private String name;
        private String url;

        public ArtTrendListDto(ArtTrend artTrend) {
            this.name = artTrend.getName();
            this.url = artTrend.getUrl();
        }
    }
}
