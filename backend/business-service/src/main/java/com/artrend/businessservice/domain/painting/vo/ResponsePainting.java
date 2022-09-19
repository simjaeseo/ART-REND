package com.artrend.businessservice.domain.painting.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePainting {
    private String id;
    private String title;
    private String artist;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate year;
    private String dimensions;
    private String medium;
    private String place;
    private Long hits;
    private Long totalLikeCount;
    private Long totalChangeCount;
}
