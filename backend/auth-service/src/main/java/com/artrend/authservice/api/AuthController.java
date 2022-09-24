package com.artrend.authservice.api;

import com.artrend.authservice.global.common.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name="auth-service")

public class AuthController {

    @GetMapping("/ccccccc")
    public ResponseEntity<MessageResponse> status(){
        return ResponseEntity.ok().body(new MessageResponse("aaaaaa"));
    }





    // 구글 로그인 로직




}
