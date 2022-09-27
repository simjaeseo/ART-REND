package com.artrend.businessservice.domain.painting.dto;

import lombok.Data;

import java.util.List;

@Data
public class SelectedPaintingDto {
    private Long memberId;
    private List<Long> paintings;
}
