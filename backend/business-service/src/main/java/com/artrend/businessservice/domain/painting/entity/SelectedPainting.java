package com.artrend.businessservice.domain.painting.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SelectedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_painting_id")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = LAZY)
    private FavoriteStyle favoriteStyle;
}
