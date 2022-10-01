package com.artrend.businessservice.domain.log.service;

import com.artrend.businessservice.domain.log.entity.MemberLog;
import com.artrend.businessservice.domain.log.repository.MemberLogRepository;
import com.artrend.businessservice.domain.log.vo.MemberLogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberLogServiceImpl implements MemberLogService {
    private final MemberLogRepository memberLogRepository;

    @Override
    @Transactional
    public void saveMemberLogs(MemberLogRequest memberLogRequest) {
        MemberLog log = MemberLog.builder()
                .memberId(memberLogRequest.getMemberId())
                .paintingId(memberLogRequest.getPaintingId())
                .clickCount(memberLogRequest.getClickCount())
                .changeCount(memberLogRequest.getChangeCount())
                .zoomCount(memberLogRequest.getZoomCount())
                .inTime(memberLogRequest.getInTime())
                .outTime(memberLogRequest.getOutTime())
                .build();

        memberLogRepository.save(log);
    }
}
