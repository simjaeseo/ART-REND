package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.ArtTrend;
import com.artrend.businessservice.domain.description.repository.custom.ArtTrendRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtTrendRepository extends JpaRepository<ArtTrend, Long>, ArtTrendRepositoryCustom {
}
