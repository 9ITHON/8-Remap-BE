package com.example.ReMap.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenDTO {
    Long memberId;
    String refreshToken;
}
