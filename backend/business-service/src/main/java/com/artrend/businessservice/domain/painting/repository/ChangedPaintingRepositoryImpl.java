package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.entity.ChangedPainting;
import com.artrend.businessservice.domain.painting.repository.custom.ChangedPaintingRepositoryCustom;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.artrend.businessservice.domain.painting.entity.QChangedPainting.changedPainting;

public class ChangedPaintingRepositoryImpl extends QuerydslRepositorySupport implements ChangedPaintingRepositoryCustom {

    public ChangedPaintingRepositoryImpl() {
        super(ChangedPainting.class);
    }

    @Override
    public Page<ChangedPainting> findChangedPaintings(Long memberId, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(changedPainting)
                .where(
                        memberEq(memberId)
                ),
                countQuery -> countQuery
                        .selectFrom(changedPainting)
                        .where(
                                memberEq(memberId)
                        )
        );
    }

    @Override
    public Page<ChangedPainting> findChangedPaintingsAll(Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(changedPainting)
                , countQuery -> countQuery
                        .selectFrom(changedPainting)
        );
    }

    private BooleanExpression memberEq(Long memberId) {
        return memberId != null ? changedPainting.memberId.eq(memberId) : null;
    }
}
