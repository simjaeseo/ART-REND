package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.ChangedPainting;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangedPaintingDto {
    private Long id;
    private Long originalId;
    private Long memberId;
    private String title;
    private String koreanTitle;
    private String artist;
    private Blob url;

    @QueryProjection
    public ChangedPaintingDto(ChangedPainting changedPainting) {
        this.id = changedPainting.getId();
        this.originalId = changedPainting.getPainting().getId();
        this.memberId = changedPainting.getMemberId();;
        this.title = changedPainting.getPainting().getTitle();
        this.koreanTitle = changedPainting.getPainting().getKoreanTitle();
        this.artist = changedPainting.getPainting().getArtist();
        this.url = changedPainting.getUrl();
    }
}
