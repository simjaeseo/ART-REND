package com.artrend.authservice.domain;

import com.artrend.authservice.global.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String providerId;

//    @Column(nullable = false)
//    private String email;

//    @Column
//    private String picture;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;

}