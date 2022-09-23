package com.artrend.businessservice.domain.painting.entity;

import com.artrend.businessservice.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class Painting extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "painting_id")
    private Long id;

    private String title;

    private String koreanTitle;

    private String artTrend;

    private String artist;

    @Column(length = 5000)
    private String description;

    private String genre;

    private String year;

    private String dimensions;

    private String medium;

    private String place;

    private String url;

    private Long hits;

    private Long totalLikeCount;

    private Long totalChangeCount;

    @OneToMany(mappedBy = "painting")
    private List<LikedPainting> likedPainting = new ArrayList<>();

//    @OneToMany(mappedBy = "recommended", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<DetailRecommendedPainting> detailRecommendedPaintings = new ArrayList<>();
//
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "detail_recommended_painting")
    private DetailRecommendedPainting detailRecommendedPainting;

    public void updateTotalLikeCount(Integer count) {
        this.totalLikeCount += count;
    }
}
