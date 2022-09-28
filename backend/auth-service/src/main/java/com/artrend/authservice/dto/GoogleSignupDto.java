package com.artrend.authservice.dto;

import lombok.Data;

@Data
public class GoogleSignupDto {
    private String name;
    private String provider;
    private String providerId;
    private String di;

}
