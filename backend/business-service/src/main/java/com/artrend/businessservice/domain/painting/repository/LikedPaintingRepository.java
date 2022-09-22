package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.member.entity.Member;
import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedPaintingRepository extends JpaRepository<LikedPainting, Long> {
//    Optional<LikedPainting> findLikedPaintingByMemberAndPaintingId(Member member, Long paintingId);
}