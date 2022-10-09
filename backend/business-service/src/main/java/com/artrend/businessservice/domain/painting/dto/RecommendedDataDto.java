package com.artrend.businessservice.domain.painting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendedDataDto {
    private PaintingDto painting;
    private Object recommendedList;

    public RecommendedDataDto(PaintingDto painting, List<DetailPageDto> recommendedList) {
        this.painting = painting;
        this.recommendedList = recommendedList;
    }

    public RecommendedDataDto(PaintingDto painting, ResponseEntity<Object> recommendedList) {
        this.painting = painting;
        this.recommendedList = recommendedList.getBody();
    }

    public RecommendedDataDto(PaintingDto painting, Object object) {
        this.painting = painting;
        this.recommendedList = object;
    }
}
