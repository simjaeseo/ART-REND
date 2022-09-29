package com.artrend.businessservice.domain.painting.vo;

import com.artrend.businessservice.domain.painting.dto.DetailDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DetailResponse {
    private PaintingDto painting;
    private List<DetailDto> recommendList;
}
