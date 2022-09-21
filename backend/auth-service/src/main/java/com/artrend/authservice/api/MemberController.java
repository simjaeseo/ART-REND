package com.artrend.authservice.api;

import com.artrend.authservice.domain.Member;
import com.artrend.authservice.dto.request.NicknameRequest;
import com.artrend.authservice.global.common.MessageResponse;
import com.artrend.authservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/signup/{memberId}/nickname")
    public ResponseEntity<? extends MessageResponse> insertNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){
        System.out.println(nicknameRequest);
        memberService.insertNickname(memberId, nicknameRequest);
        return ResponseEntity.ok().body(new MessageResponse("닉네임 설정 완료"));
    }

    @PostMapping("/signup/{memberId}/nickname/duplicate")
    public ResponseEntity<? extends MessageResponse> checkNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){

        if(memberService.checkNickname(memberId, nicknameRequest)){
            // 닉네임 중복일때
            return ResponseEntity.badRequest().body(new MessageResponse("닉네임 중복입니다."));
        }else{
            // 닉네임 중복 아닐때
            return ResponseEntity.ok().body(new MessageResponse("닉네임이 중복되지 않습니다."));
        }

    }
}
