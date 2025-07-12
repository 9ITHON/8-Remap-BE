package com.example.ReMap.domain.tema.dto;

import com.example.ReMap.domain.tema.Tema;
import lombok.Builder;
import lombok.Getter;

/** 테마 단건 응답 DTO */
@Getter
@Builder
public class TemaResponse {

    private Long id;
    private String name;

    public static TemaResponse from(Tema t) {
        return TemaResponse.builder()
                .id(t.getId())
                .name(t.getName())
                .build();
    }
}
