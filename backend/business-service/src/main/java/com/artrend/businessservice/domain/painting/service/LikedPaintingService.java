package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.LikeDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface LikedPaintingService {
    void like(LikeDto likeDto) throws IOException;
    void cancelLike(LikeDto likeDto) throws IOException;
    List<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable);
}
