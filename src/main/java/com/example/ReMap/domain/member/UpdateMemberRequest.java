package com.example.ReMap.domain.member;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberRequest {

    @NotNull
    @Min(value = 0, message = "전화번호는 0 이상의 숫자여야 합니다.")
    private Integer phone;

    @NotNull(message = "생년월일을 입력해 주세요.")
    private Long birth;

    @NotBlank(message = "닉네임을 입력해 주세요.")
    @Size(max = 50, message = "닉네임은 최대 50자까지 가능합니다.")
    private String nickname;
}
