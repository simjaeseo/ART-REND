package com.artrend.authservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="auth-service")

public class AuthController {



    @GetMapping("/ccccccc")
    public String status(){
        return "It's Working in User Service";
    }





    // 구글 로그인 로직




}
