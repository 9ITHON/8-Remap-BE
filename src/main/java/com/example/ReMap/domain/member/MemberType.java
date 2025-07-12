package com.example.ReMap.domain.member;

public enum MemberType {
    USER("일반유저"),        // 오디오 감상, 감상후기 작성
    RECORDER("기록자"),      // 음성파일 업로드
    ADMIN("관리자");         // 전체 관리

    private final String description;

    MemberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}