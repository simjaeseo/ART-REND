package com.artrend.businessservice.domain.log.entity;

import com.artrend.businessservice.domain.log.vo.MemberLogRequest;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;
    private Long memberId;
    private Long paintingId;
    private Long clickCount;
    private Long zoomCount;
    private Long changeCount;
    private String inTime;
    private String outTime;
}
