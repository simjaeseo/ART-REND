package com.artrend.authservice.dto.request;

import lombok.Data;

@Data
public class MemberInfoCheckRequest {
    private String name;
    private String birth;
}
