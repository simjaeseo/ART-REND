package com.artrend.businessservice.domain.description.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ArtistRepository {

    private final JPAQueryFactory queryFactory;

    public ArtistRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public String getDescription() {
        return null;
    }
}
