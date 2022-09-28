package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface LikedPaintingService {
    ResponseEntity<Object> like(MemberDto memberDto) throws IOException;
    void cancelLike(MemberDto memberDto) throws IOException;
    List<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable);
}
