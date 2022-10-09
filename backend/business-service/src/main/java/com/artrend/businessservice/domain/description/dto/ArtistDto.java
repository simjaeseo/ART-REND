package com.artrend.businessservice.domain.description.dto;

import com.artrend.businessservice.domain.description.entity.Artist;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistDto {
    private String name;
    private String koreanName;
    private String description;
    private String birth;
    private String death;
    private String url;

    @Data
    @NoArgsConstructor
    public static class ArtistListDto {
        private String name;
        private String koreanName;
        private String url;

        public ArtistListDto(Artist artist) {
            this.name = artist.getName();
            this.koreanName = artist.getKoreanName();
            this.url = artist.getUrl();
        }
    }
}
