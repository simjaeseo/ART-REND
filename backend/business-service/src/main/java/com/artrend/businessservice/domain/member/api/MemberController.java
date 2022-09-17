package com.artrend.businessservice.domain.member.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-service")
@AllArgsConstructor
@Slf4j
public class MemberController {

    final Environment env;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("check")
    public String check(){
        log.info("Server port={}", env.getProperty("local.server.port"));

        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }

}
