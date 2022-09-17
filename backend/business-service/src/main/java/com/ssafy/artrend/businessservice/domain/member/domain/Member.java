package com.ssafy.artrend.businessservice.domain.member.domain;

import com.ssafy.artrend.businessservice.global.common.BaseEntity;

import javax.persistence.*;
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;


}
