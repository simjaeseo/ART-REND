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
    private String inTime;
    private String outTime;

    public MemberLog(MemberLogRequest request) {
        this.memberId = request.getMemberId();
        this.paintingId = request.getPaintingId();
        this.clickCount = request.getClickCount();
        this.zoomCount = request.getZoomCount();
        this.inTime = request.getInTime();
        this.outTime = request.getOutTime();
    }
}
