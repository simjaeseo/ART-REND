package com.artrend.businessservice.domain.painting.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FavoriteStyle {
    @Id
    @Column(name = "favorite_style_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @MapsId
    @JoinColumn(name = "painting_id")
    private Painting painting;
}
