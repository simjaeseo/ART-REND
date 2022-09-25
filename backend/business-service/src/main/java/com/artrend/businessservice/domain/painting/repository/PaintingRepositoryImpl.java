package com.artrend.businessservice.domain.painting.repository;


import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.SearchCondition;

import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;
import static org.springframework.util.StringUtils.hasText;

public class PaintingRepositoryImpl extends QuerydslRepositorySupport implements PaintingRepositoryCustom {
    public PaintingRepositoryImpl() {
        super(Painting.class);
    }

    @Override
    public Page<Painting> searchPaintings(SearchCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(painting)
                        .where(
                                artistEq(condition.getArtist()),
                                artTrendEq(condition.getArtTrend()),
                                genreEq(condition.getGenre())
                        ),
                countQuery -> countQuery
                        .selectFrom(painting)
                        .where(
                                artistEq(condition.getArtist()),
                                artTrendEq(condition.getArtTrend()),
                                genreEq(condition.getGenre())
                        )
        );
    }

    @Override
    public Page<Painting> sortPaintings(PaintingCondition condition, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(painting)
                        .orderBy(
                                paintingSort(pageable)
                        ),
                countQuery -> countQuery
                        .selectFrom(painting)
                        .orderBy(
                                paintingSort(pageable)
                        )
        );
    }

    private BooleanExpression artistEq(String artist) {
        return hasText(artist) ? painting.artist.eq(artist) : null;
    }

    private BooleanExpression artTrendEq(String artTrend) {
        return hasText(artTrend) ? painting.artTrend.eq(artTrend) : null;
    }

    private BooleanExpression genreEq(String genre) {
        return hasText(genre) ? painting.genre.eq(genre) : null;
    }

    private OrderSpecifier<?> paintingSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "hits":
                        return new OrderSpecifier(direction, painting.hits);
                    case "totalLikeCount":
                        return new OrderSpecifier(direction, painting.totalLikeCount);
                    case "totalChangeCount":
                        return new OrderSpecifier(direction, painting.totalChangeCount);
                }
            }
        }

        return null;
    }
}
