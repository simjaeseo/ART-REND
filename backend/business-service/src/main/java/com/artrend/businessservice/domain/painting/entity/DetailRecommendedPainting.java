package com.artrend.businessservice.domain.painting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailRecommendedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_recommended_painting_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private Painting painting;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recommended_id")
    private Painting recommended;

}
