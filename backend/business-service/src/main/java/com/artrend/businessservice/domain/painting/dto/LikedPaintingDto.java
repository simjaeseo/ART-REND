package com.artrend.businessservice.domain.painting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedPaintingDto {
    private Long paintingId;
    private Long memberId;
}
