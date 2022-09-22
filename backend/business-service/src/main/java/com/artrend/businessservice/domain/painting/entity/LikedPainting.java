package com.artrend.businessservice.domain.painting.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LikedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_painting_id")
    private Long id;

    @NonNull
    private Long paintingId;

    private Long memberId;
}
