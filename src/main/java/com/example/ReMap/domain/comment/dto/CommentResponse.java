package com.example.ReMap.domain.comment.dto;


import com.example.ReMap.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/** 댓글 단건 응답 DTO */
@Getter
@Builder
public class CommentResponse {

    private Long id;               // 댓글 ID
    private Long memberId;         // 작성자 ID
    private String nickname;       // 작성자 닉네임
    private String content;        // 댓글 본문
    private LocalDateTime createdAt;

    /** Comment 엔티티 → DTO 변환 */
    public static CommentResponse from(Comment c) {
        return CommentResponse.builder()
                .id(c.getId())
                .memberId(c.getMember() != null ? c.getMember().getId() : null)
                .nickname(c.getMember() != null ? c.getMember().getNickname() : "탈퇴한 회원")
                .content(c.getContent())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
