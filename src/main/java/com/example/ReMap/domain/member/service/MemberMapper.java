package com.example.ReMap.domain.member.service;

import com.example.ReMap.domain.member.dto.RefreshTokenDTO;
import com.example.ReMap.domain.member.entity.Member;

public class MemberMapper {

    public static Member toLoginEmailMember(String email, String encodedPassword, String nickname, Long birth) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .birth(birth)
                .build();
    }

    public static RefreshTokenDTO toRefreshToken(String refreshToken) {
        return RefreshTokenDTO.builder()
                .refreshToken(refreshToken)
                .build();
    }
}
