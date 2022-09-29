package com.artrend.businessservice.domain.description.dto;

import com.artrend.businessservice.domain.description.entity.Artist;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ArtistDto {
    private String name;
    private String description;
    private String birth;
    private String death;
    private String url;

    @QueryProjection
    public ArtistDto(Artist artist) {
        this.name = artist.getArtist();
        this.description = artist.getDescription();
        this.birth = artist.getBirth();
        this.death = artist.getDeath();
        this.url = artist.getUrl();
    }
}
