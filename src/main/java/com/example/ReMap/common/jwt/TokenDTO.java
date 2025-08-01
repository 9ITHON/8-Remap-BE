package com.example.ReMap.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDTO {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}
