package com.artrend.businessservice.domain.description.dto;


import com.artrend.businessservice.domain.description.entity.Genre;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreDto {
    private String name;
    private String description;
    private String url;

    @Data
    @NoArgsConstructor
    public static class GenreListDto {
        private String name;
        private String url;

        public GenreListDto(Genre genre) {
            this.name = genre.getName();
            this.url = genre.getUrl();
        }
    }
}
