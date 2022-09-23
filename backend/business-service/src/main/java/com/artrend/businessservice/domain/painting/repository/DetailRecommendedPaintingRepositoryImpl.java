package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.QPaintingDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.artrend.businessservice.domain.painting.entity.QDetailRecommendedPainting.detailRecommendedPainting;
import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;

public class DetailRecommendedPaintingRepositoryImpl implements DetailRecommendedPaintingRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public DetailRecommendedPaintingRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<PaintingDto> findDetailRecommendedPaintings(Long paintingId, Pageable pageable) {
        List<PaintingDto> content = queryFactory
                .select(new QPaintingDto(detailRecommendedPainting))
                .from(detailRecommendedPainting)
                .leftJoin(detailRecommendedPainting.painting, painting)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(detailRecommendedPainting.count())
                .from(detailRecommendedPainting)
                .leftJoin(detailRecommendedPainting.painting, painting)
                .where(paintingIdEq(paintingId));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private BooleanExpression paintingIdEq(Long paintingId) {
        return paintingId != null ? detailRecommendedPainting.id.eq(paintingId) : null;
    }
}
