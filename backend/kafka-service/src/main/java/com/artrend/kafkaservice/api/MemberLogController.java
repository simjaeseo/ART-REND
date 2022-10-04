package com.artrend.kafkaservice.api;

import com.artrend.kafkaservice.dto.request.MemberLogRequest;
import com.artrend.kafkaservice.global.common.MessageResponse;
import com.artrend.kafkaservice.service.MemberLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "ActionLog", description = "액션로그 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberLogController {

    private final MemberLogService memberLogService;

    @Operation(summary = "사용자 로그 보내기", description = "페이지에서 나갈 때 사용자 로그를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/log")
    public ResponseEntity<? extends MessageResponse> saveMemberLog(@RequestBody MemberLogRequest request) {
        memberLogService.saveMemberLogs(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("성공"));
    }

}
