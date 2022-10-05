package com.artrend.businessservice.domain.painting.vo;

import com.artrend.businessservice.domain.painting.dto.DetailDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponse implements Serializable {
    private PaintingDto painting;
    private List<DetailDto> recommendList;
}
