package com.artrend.businessservice.domain.painting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponse {
    private PaintingDto painting;
    private List<DetailDto> recommendList;
}
