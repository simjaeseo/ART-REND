package com.artrend.businessservice.domain.painting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendDto {
    private PaintingDto painting;
    private Object recommendedList;

    public RecommendDto(PaintingDto painting, List<DetailDto> recommendedList) {
        this.painting = painting;
        this.recommendedList = recommendedList;
    }
    public RecommendDto(PaintingDto painting, ResponseEntity<Object> recommendedList) {
        this.painting = painting;
        this.recommendedList = recommendedList.getBody();
    }
}
