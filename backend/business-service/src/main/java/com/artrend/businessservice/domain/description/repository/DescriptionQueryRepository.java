package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

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
                        artistContains(condition.getName())
                ).fetchOne();
    }

    public GenreDto searchGenreV1(DetailCondition condition) {
        List<GenreDto> fetch = queryFactory
                .select(new QGenreDto(genre))
                .from(genre)
                .where(
                        genreContains(condition.getName())
                ).fetch();

        GenreDto genreDto = null;

        if (fetch.size() > 1) {
            genreDto = queryFactory
                    .select(new QGenreDto(genre))
                    .from(genre)
                    .where(
                            genreEq(condition.getName())
                    ).fetchOne();
        }

        return genreDto;
    }


    public ArtTrendDto searchArtTrendV1(DetailCondition condition) {
        List<ArtTrendDto> fetch = queryFactory
                .select(new QArtTrendDto(artTrend))
                .from(artTrend)
                .where(
                        artTrendContains(condition.getName())
                ).fetch();

        ArtTrendDto artTrendDto = null;

        if (fetch.size() > 1) {
            artTrendDto = queryFactory
                    .select(new QArtTrendDto(artTrend))
                    .from(artTrend)
                    .where(
                            artTrendEq(condition.getName())
                    ).fetchOne();
        }

        return artTrendDto;
    }

    public ArtTrendDto searchArtTrendV2(DetailCondition condition) {
        return queryFactory
                .select(new QArtTrendDto(artTrend))
                .from(artTrend)
                .where(
                ).fetchOne();
    }

    private BooleanExpression artistContains(String name) {
        return hasText(name) ? artist.name.contains(name) : null;
    }

    private BooleanExpression genreContains(String name) {
        return hasText(name) ? genre.name.contains(name) : null;
    }

    private BooleanExpression genreEq(String name) {
        return hasText(name) ? genre.name.eq(name) : null;
    }

    private BooleanExpression artTrendContains(String name) {
        return hasText(name) ? artTrend.name.contains(name) : null;
    }

    private BooleanExpression artTrendContainsLt(String name) {
        return hasText(name) ? artTrend.name.contains(name).count().lt(2) : null;
    }

    private BooleanExpression artTrendEq(String name) {
        return hasText(name) ? artTrend.name.eq(name) : null;
    }
}
