package com.artrend.businessservice.domain.painting.entity;

import com.artrend.businessservice.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_painting_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private Painting painting;
}
