package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteStyleRepository extends JpaRepository<FavoriteStyle, Long> {
}
