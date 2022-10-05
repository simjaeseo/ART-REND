package com.artrend.businessservice.domain.log.vo;

import lombok.Getter;

@Getter
public class MemberLogRequest {
    private Long memberId;
    private Long paintingId;
    private Long clickCount;
    private Long changeCount;
    private Long zoomCount;
    private String inTime;
    private String outTime;
}
