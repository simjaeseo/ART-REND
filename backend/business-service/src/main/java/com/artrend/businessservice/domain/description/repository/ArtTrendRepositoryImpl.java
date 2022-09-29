package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.ArtTrend;
import com.artrend.businessservice.domain.description.repository.custom.ArtTrendRepositoryCustom;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.artrend.businessservice.domain.description.entity.QArtTrend.artTrend;

public class ArtTrendRepositoryImpl extends QuerydslRepositorySupport implements ArtTrendRepositoryCustom {
    public ArtTrendRepositoryImpl() {
        super(ArtTrend.class);
    }

    @Override
    public Page<ArtTrend> searchArtTrendList(Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(artTrend),
                countQuery -> countQuery
                        .selectFrom(artTrend)
        );
    }
}
