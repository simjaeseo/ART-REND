package com.artrend.businessservice.domain.painting.dto;

import lombok.Data;

@Data
public class SearchCondition {
    private String artist;
    private String artTrend;
    private String genre;
}
