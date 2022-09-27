package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.RecommendedPainting;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.artrend.businessservice.domain.painting.entity.QRecommendedPainting.recommendedPainting;

public class RecommendedPaintingRepositoryImpl extends QuerydslRepositorySupport implements RecommendedPaintingRepositoryCustom {
    public RecommendedPaintingRepositoryImpl() {
        super(RecommendedPainting.class);
    }

    @Override
    public Page<RecommendedPainting> findRecommendedPaintings(Long memberId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(recommendedPainting)
                .where(
                        memberEq(memberId)
                ),
                countQuery -> countQuery
                        .selectFrom(recommendedPainting)
                        .where(
                                memberEq(memberId)
                        )
        );
    }

    private BooleanExpression memberEq(Long memberId) {
        return memberId != null ? recommendedPainting.memberId.eq(memberId) : null;
    }
}
