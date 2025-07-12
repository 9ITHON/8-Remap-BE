package com.example.ReMap.domain.member.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberSignDTO {

    @Getter
    public static class SignUpDTO {
        @NotBlank
        @Size(max = 10, message = "닉네임은 10자 이내여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9-_가-힣]*$", message = "닉네임은 영문, 한글, 숫자, '-', '_'만 포함할 수 있습니다.")
        String nickname;

        @NotBlank
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        String email;

        @NotBlank
        @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이내여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
        String password;

        @NotNull
        Long birth;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDTO {
        String email;
        String password;
    }
}
