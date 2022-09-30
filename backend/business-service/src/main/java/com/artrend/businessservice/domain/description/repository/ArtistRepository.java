package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.Artist;
import com.artrend.businessservice.domain.description.repository.custom.ArtistRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> , ArtistRepositoryCustom {
}
