package com.artrend.businessservice.domain.painting.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private Painting painting;

    private Long memberId;

    public void update(Long memberId) {
        if (this.memberId == null) {
            this.memberId = memberId;
        }
    }

}
