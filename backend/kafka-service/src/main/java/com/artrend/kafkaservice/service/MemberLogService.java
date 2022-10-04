package com.artrend.kafkaservice.service;

import com.artrend.kafkaservice.dto.request.MemberLogRequest;
import com.artrend.kafkaservice.messagequeue.MemberLogProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberLogService {
    private final MemberLogProducer memberLogProducer;


    public void saveMemberLogs(MemberLogRequest memberLogRequest) {
//        MemberLog log = MemberLog.builder()
//                .memberId(memberLogRequest.getMemberId())
//                .paintingId(memberLogRequest.getPaintingId())
//                .clickCount(memberLogRequest.getClickCount())
//                .changeCount(memberLogRequest.getChangeCount())
//                .zoomCount(memberLogRequest.getZoomCount())
//                .inTime(memberLogRequest.getInTime())
//                .outTime(memberLogRequest.getOutTime())
//                .build();

        memberLogProducer.memberLogSend("memberLog", memberLogRequest);

//        memberLogRepository.save(log);
    }
}
