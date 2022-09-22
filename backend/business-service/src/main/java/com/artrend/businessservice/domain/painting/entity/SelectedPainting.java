package com.artrend.businessservice.domain.painting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_painting_id")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private FavoriteStyle favoriteStyle;
}
