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

    @ManyToOne(fetch = LAZY)
    private FavoriteStyle favoriteStyle;

    @OneToMany(mappedBy = "painting")
    private List<LikedPainting> likedPainting = new ArrayList<>();

    public void updateTotalLikeCount(Integer count) {
        this.totalLikeCount += count;
    }
}
