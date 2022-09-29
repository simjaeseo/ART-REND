package com.artrend.businessservice.domain.log.service;

import com.artrend.businessservice.domain.log.vo.MemberLogRequest;

public interface MemberLogService {
    void saveMemberLogs(MemberLogRequest memberLogRequest);
}
