package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.LikedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.QLikedPaintingDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.artrend.businessservice.domain.painting.entity.QLikedPainting.likedPainting;
import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;

public class LikedPaintingRepositoryImpl implements LikedPaintingRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public LikedPaintingRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LikedPaintingDto> findLikedPaintings(Long memberId, Pageable pageable) {
        List<LikedPaintingDto> content = queryFactory
                .select(new QLikedPaintingDto(likedPainting.painting))
                .from(likedPainting)
                .leftJoin(likedPainting.painting, painting)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(likedPainting.count())
                .from(likedPainting)
                .leftJoin(likedPainting.painting, painting)
                .where(memberIdEq(memberId));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? likedPainting.memberId.eq(memberId) : null;
    }
}
