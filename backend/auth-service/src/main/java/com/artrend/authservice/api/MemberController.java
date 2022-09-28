package com.artrend.authservice.api;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.dto.request.MemberInfoRequest;
import com.artrend.authservice.dto.request.NicknameRequest;
import com.artrend.authservice.dto.response.SelectNicknameResponse;
import com.artrend.authservice.global.common.CountResponse;
import com.artrend.authservice.global.common.DataResponse;
import com.artrend.authservice.global.common.MessageResponse;
import com.artrend.authservice.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "닉네임 추가하기", description = "닉네임을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 설정 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PutMapping("/signup/{memberId}/nickname")
    public ResponseEntity insertNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){
        memberService.insertNickname(memberId, nicknameRequest);
        return new ResponseEntity<>(new MessageResponse("닉네임 설정 완료"), HttpStatus.OK);
    }

    @Operation(summary = "닉네임 중복 체크하기", description = "사용하려는 닉네임 중복 체크를 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "총 회원 수 조회 성공"),
            @ApiResponse(responseCode = "400", description = "닉네임이 중복되지 않습니다."),
            @ApiResponse(responseCode = "409", description = "닉네임 중복입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/signup/{memberId}/nickname/duplicate")
    public ResponseEntity checkNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){

        if(memberService.checkNickname(memberId, nicknameRequest)){
            // 닉네임 중복일때
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("닉네임 중복입니다."));
        }else{
            // 닉네임 중복 아닐때
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("닉네임이 중복되지 않습니다."));
        }
    }

    @Operation(summary = "닉네임 수정하기", description = "해당 회원의 닉네임을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 변경 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PutMapping("/{memberId}/nickname")
    public ResponseEntity updateNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){
        memberService.updateNickname(memberId, nicknameRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("닉네임 변경 완료"));
    }

    @Operation(summary = "총 회원 수 조회하기", description = "현재 총 회원 수를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "총 회원 수 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/members/count")
    public ResponseEntity selectMemberCount(){
        int memberCount = memberService.selectMemberCount();
        return ResponseEntity.status(HttpStatus.OK).body(new CountResponse("총 회원 수 조회 성공", memberCount));
    }

    @Operation(summary = "회원 탈퇴하기", description = "해당 회원을 탈퇴처리합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원탈퇴가 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @DeleteMapping("withdrawal/{memberId}")
    public ResponseEntity memberWithdrawal(@PathVariable Long memberId){
        memberService.memberWithdrawal(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>("회원탈퇴가 완료되었습니다."));
    }

    @Operation(summary = "닉네임 조회하기", description = "해당 회원의 닉네임을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 조회 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{memberId}/nickname")
    public ResponseEntity selectNickname(@PathVariable Long memberId){
        String selectNickname = memberService.selectNickname(memberId);
        SelectNicknameResponse selectNicknameResponse = new SelectNicknameResponse(selectNickname);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("닉네임 조회 완료", selectNicknameResponse));
    }

    @Operation(summary = "회원 정보 추가하기", description = "회원의 이름, 생년월일을 기반으로 소셜로그인 회원가입/통합처리/로그인을 진행한 후 jwt를 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("signup/members/info")
    public ResponseEntity insertMemberInfo(@RequestBody MemberInfoRequest memberInfoRequest, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {

        Map<String, Object> attributes = memberService.insertMemberInfo(memberInfoRequest);


        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("성공", attributes));
    }
}
