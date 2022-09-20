package com.artrend.businessservice.domain.painting.entity;

import com.artrend.businessservice.domain.member.entity.Member;
import com.artrend.businessservice.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangedPainting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changed_painting_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Painting painting;
    
    private String url;
}
