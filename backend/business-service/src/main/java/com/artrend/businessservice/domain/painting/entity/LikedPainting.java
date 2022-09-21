package com.artrend.businessservice.domain.painting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikedPainting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changed_painting_id")
    private Long id;
}
