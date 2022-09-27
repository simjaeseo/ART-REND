package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.LikedPainting;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import static com.artrend.businessservice.domain.painting.entity.QLikedPainting.likedPainting;
import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;

public class LikedPaintingRepositoryImpl extends QuerydslRepositorySupport implements LikedPaintingRepositoryCustom {

    public LikedPaintingRepositoryImpl() {
        super(LikedPainting.class);
    }

    @Override
    public Page<LikedPainting> findLikedPaintings(Long memberId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(likedPainting)
                .leftJoin(likedPainting.painting, painting)
                .where(
                        memberIdEq(memberId)
                ),
                countQuery -> countQuery
                        .selectFrom(likedPainting)
                        .leftJoin(likedPainting.painting, painting)
                        .where(
                                memberIdEq(memberId)
                        )
        );
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? likedPainting.memberId.eq(memberId) : null;
    }
}
