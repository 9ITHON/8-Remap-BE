package com.example.ReMap.domain.member;

import jakarta.validation.constraints.NotNull;

public class ChangeMemberTypeRequest {

    @NotNull(message = "회원 유형을 선택해주세요.")
    private MemberType memberType;

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
