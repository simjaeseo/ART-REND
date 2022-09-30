package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.artrend.businessservice.domain.description.entity.QArtTrend.artTrend;
import static com.artrend.businessservice.domain.description.entity.QArtist.artist;
import static com.artrend.businessservice.domain.description.entity.QGenre.genre;
import static org.springframework.util.StringUtils.*;

@Repository
public class DescriptionQueryRepository {
    private final JPAQueryFactory queryFactory;

    public DescriptionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public ArtistDto searchArtist(DetailCondition condition) {
        return queryFactory
                .select(new QArtistDto(artist))
                .from(artist)
                .where(
                        artistEq(condition.getName())
                ).fetchOne();
    }

    public GenreDto searchGenreV1(DetailCondition condition) {
        return queryFactory
                .select(new QGenreDto(genre))
                .from(genre)
                .where(
                        genreEq(condition.getName())
                ).fetchOne();
    }

    public ArtTrendDto searchArtTrendV1(DetailCondition condition) {
        return queryFactory
                .select(new QArtTrendDto(artTrend))
                .from(artTrend)
                .where(
                        artTrendEq(condition.getName())
                ).fetchOne();
    }

    private BooleanExpression genreEq(String name) {
        return hasText(name) ? genre.name.eq(name) : null;
    }

    private BooleanExpression artistEq(String name) {
        return hasText(name) ? artist.name.eq(name) : null;
    }

    private BooleanExpression artTrendEq(String name) {
        return hasText(name) ? artTrend.name.eq(name) : null;
    }
}
