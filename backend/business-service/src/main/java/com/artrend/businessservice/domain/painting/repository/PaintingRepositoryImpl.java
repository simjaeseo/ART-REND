package com.artrend.businessservice.domain.painting.repository;

import com.artrend.businessservice.domain.painting.dto.PaintingCondition;
import com.artrend.businessservice.domain.painting.dto.PaintingDto;
import com.artrend.businessservice.domain.painting.dto.QPaintingDto;
import com.artrend.businessservice.domain.painting.entity.QPainting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.artrend.businessservice.domain.painting.entity.QPainting.painting;

public class PaintingRepositoryImpl implements PaintingRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public PaintingRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<PaintingDto> searchPaintings(PaintingCondition condition, Pageable pageable) {
        List<PaintingDto> content;


        return null;
    }
}
