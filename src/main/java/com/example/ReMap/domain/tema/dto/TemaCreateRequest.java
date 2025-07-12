package com.example.ReMap.domain.tema.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/** 테마 생성 요청 */
@Getter @Setter
public class TemaCreateRequest {

    @NotBlank(message = "테마 이름을 입력해 주세요.")
    private String name;
}
