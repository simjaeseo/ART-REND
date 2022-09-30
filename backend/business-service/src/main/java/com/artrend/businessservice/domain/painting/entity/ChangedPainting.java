package com.artrend.businessservice.domain.painting.entity;

import com.artrend.businessservice.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Blob;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangedPainting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changed_painting_id")
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private Painting painting;

    @Column(columnDefinition = "LONGTEXT")
    private String url;
}
