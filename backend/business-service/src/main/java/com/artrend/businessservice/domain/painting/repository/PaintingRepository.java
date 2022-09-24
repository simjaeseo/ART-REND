package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.FavoriteStyle;
import com.artrend.businessservice.domain.painting.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long>, PaintingRepositoryCustom {
}
