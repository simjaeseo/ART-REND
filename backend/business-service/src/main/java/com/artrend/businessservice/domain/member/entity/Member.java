package com.artrend.businessservice.domain.member.entity;

import com.artrend.businessservice.global.common.BaseEntity;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;
}
