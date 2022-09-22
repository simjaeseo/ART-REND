package com.artrend.businessservice.domain.painting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_painting_id")
    private Long id;
}
