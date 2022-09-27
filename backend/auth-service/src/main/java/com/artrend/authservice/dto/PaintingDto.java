package com.artrend.authservice.dto;

import com.artrend.authservice.global.common.BaseEntity;
import lombok.Data;

@Data
public class PaintingDto extends BaseEntity {
    private Long id;

    private String title;

    private String koreanTitle;

    private String artTrend;

    private String artist;

    private String description;

    private String genre;

    private String year;

    private String dimensions;

    private String medium;

    private String place;

    private String url;

    private Long hits;

    private Long totalLikeCount;

    private Long totalChangeCount;
}
