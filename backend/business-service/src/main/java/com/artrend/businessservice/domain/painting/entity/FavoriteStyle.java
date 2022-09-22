package com.artrend.businessservice.domain.painting.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteStyle implements Serializable {
    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "painting_id")
    private Painting painting;
}
