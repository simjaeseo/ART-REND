package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikeDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;

import java.io.IOException;
import java.util.List;

public interface LikedPaintingService {
    void like(LikeDto likeDto, String token) throws IOException;
    void cancelLike(LikeDto likeDto, String token) throws IOException;
    List<LikedPaintingDto> findLikedPaintings();
}
