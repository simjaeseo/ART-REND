package com.artrend.businessservice.domain.description.dto;

import com.artrend.businessservice.domain.painting.dto.SearchCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailCondition {
    private String name;

    public DetailCondition(SearchCondition condition) {
        if (condition.getArtist() != null) {
            this.name = condition.getArtist();
        } else if (condition.getGenre() != null) {
            this.name = condition.getGenre();
        } else if (condition.getArtTrend() != null) {
            this.name = condition.getArtTrend();
        }
    }
}
