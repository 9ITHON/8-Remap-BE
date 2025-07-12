package com.example.ReMap.domain.comment.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/** 댓글 등록 요청 DTO */
@Getter @Setter
public class CreateCommentRequest {

    /** 댓글 본문 (빈 문자열 금지) */
    @NotBlank(message = "댓글 내용을 입력해 주세요.")
    private String content;
}

