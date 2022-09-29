package com.artrend.businessservice.domain.description.repository.custom;

import com.artrend.businessservice.domain.description.dto.DetailCondition;
import com.artrend.businessservice.domain.description.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreRepositoryCustom {
    Page<Genre> searchGenreList(Pageable pageable);
}
