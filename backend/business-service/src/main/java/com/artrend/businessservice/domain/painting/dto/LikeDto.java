package com.artrend.businessservice.domain.painting.dto;

import com.artrend.businessservice.domain.painting.entity.Painting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    private Long paintingId;
    private Long memberId;
}
