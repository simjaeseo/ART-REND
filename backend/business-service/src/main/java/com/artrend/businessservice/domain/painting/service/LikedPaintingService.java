package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;

import java.io.IOException;

public interface LikedPaintingService {
    void like(LikedPaintingDto likedPaintingDto, String token) throws IOException;
    void cancelLike(LikedPaintingDto likedPaintingDto, String token) throws IOException;
}
