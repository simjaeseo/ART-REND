package com.artrend.businessservice.domain.log.api;

import com.artrend.businessservice.domain.log.service.MemberLogService;
import com.artrend.businessservice.domain.log.vo.MemberLogRequest;
import com.artrend.businessservice.global.common.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/log")
@Slf4j
public class MemberLogController {
    private final MemberLogService memberLogService;

    @Operation(summary = "사용자 로그 보내기", description = "페이지에서 나갈 때 사용자 로그를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping
    public ResponseEntity<? extends MessageResponse> saveMemberLog(@RequestBody MemberLogRequest request) {
        memberLogService.saveMemberLogs(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse());
    }
}
