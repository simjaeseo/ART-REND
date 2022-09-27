package com.artrend.businessservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class BusinessServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessServiceApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String BASE_URL = "http://127.0.0.1:8000/api/v1/paintings/";
        WebClient webClient = WebClient.create(BASE_URL);
        webClient.post()
                .uri(BASE_URL + "make_detail_recommend/");
        log.debug("*****************Post -> {} ", "호출 언제?");
    }

}
