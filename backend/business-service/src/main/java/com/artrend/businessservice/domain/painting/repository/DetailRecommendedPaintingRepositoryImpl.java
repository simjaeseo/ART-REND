package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.DetailRecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import static com.artrend.businessservice.domain.painting.entity.QDetailRecommendedPainting.detailRecommendedPainting;
import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;

public class DetailRecommendedPaintingRepositoryImpl extends QuerydslRepositorySupport implements DetailRecommendedPaintingRepositoryCustom {

    public DetailRecommendedPaintingRepositoryImpl() {
        super(DetailRecommendedPainting.class);
    }

    public Page<DetailRecommendedPainting> findDetailRecommendedPaintings(Long paintingId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(detailRecommendedPainting)
                .leftJoin(detailRecommendedPainting.recommended, painting)
                .where(
                        paintingIdEq(paintingId)
                ),
                countQuery -> countQuery
                        .selectFrom(detailRecommendedPainting)
                        .leftJoin(detailRecommendedPainting.recommended, painting)
                        .where(
                                paintingIdEq(paintingId)
                        )
        );
    }

    private BooleanExpression paintingIdEq(Long paintingId) {
        return paintingId != null ? detailRecommendedPainting.painting.id.eq(paintingId) : null;
    }
}
