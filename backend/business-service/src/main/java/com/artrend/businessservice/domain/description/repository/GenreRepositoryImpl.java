package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.Genre;
import com.artrend.businessservice.domain.description.repository.custom.GenreRepositoryCustom;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.artrend.businessservice.domain.description.entity.QGenre.genre;

public class GenreRepositoryImpl extends QuerydslRepositorySupport implements GenreRepositoryCustom {
    public GenreRepositoryImpl() {
        super(Genre.class);
    }

    @Override
    public Page<Genre> searchGenreList(Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(genre),
                countQuery -> countQuery
                        .selectFrom(genre)
        );
    }
}
