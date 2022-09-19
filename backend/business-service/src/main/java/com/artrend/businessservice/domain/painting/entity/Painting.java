package com.artrend.businessservice.domain.painting.entity;

import com.artrend.businessservice.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Painting extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "painting_id")
    private Long id;

    private String title;

    private String artist;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate year;

    private String dimensions;

    private String medium;

    private String place;

    private Long hits;

    private Long totalLikeCount;

    private Long totalChangeCount;
}
