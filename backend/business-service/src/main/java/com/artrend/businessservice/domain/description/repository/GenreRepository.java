package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.Genre;
import com.artrend.businessservice.domain.description.repository.custom.GenreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {
}
