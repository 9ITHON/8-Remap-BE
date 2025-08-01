package com.example.ReMap.domain.member.controller;

import com.example.ReMap.common.apiPayload.ApiResponse;
import com.example.ReMap.common.apiPayload.code.status.SuccessCode;
import com.example.ReMap.common.jwt.LoginService;
import com.example.ReMap.common.jwt.TokenDTO;
import com.example.ReMap.domain.member.dto.MemberSignDTO;
import com.example.ReMap.domain.member.dto.RefreshTokenDTO;
import com.example.ReMap.domain.member.service.MemberMapper;
import com.example.ReMap.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ReMap.common.apiPayload.code.status.SuccessCode._SIGNUP_SUCCESS;

@Tag(name = "유저 관련 API", description = "유저 관련 API입니다")
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @Operation(summary = "회원가입 API")
    @PostMapping("/signup")
    public ApiResponse<RefreshTokenDTO> joinByLoginId(HttpServletResponse response,
                                                      @Valid @RequestBody MemberSignDTO.SignUpDTO requestDto) {
        return ApiResponse.of(_SIGNUP_SUCCESS, memberService.insertMember(response, requestDto));
    }

    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    public ApiResponse<TokenDTO> login(@Valid @RequestBody MemberSignDTO.LoginDTO request) {
        //Filter에서 작동, swagger 틀만 작성
        MemberSignDTO.LoginDTO loginDTO = new MemberSignDTO.LoginDTO(request.getEmail(), request.getPassword());
        return ApiResponse.onSuccess(loginService.login(loginDTO));
    }

    @Operation(summary = "토큰 재발급 api", description = "Cookie에 기존 refresh 토큰 필요, 헤더의 Authorization에 access 토큰, 바디(쿠키)에 refresh 토큰 반환")
    @PostMapping("/reissue")
    public ApiResponse<RefreshTokenDTO> reissue(HttpServletRequest request, HttpServletResponse response) {
        String newRefreshToken = memberService.reissueToken(request, response);
        return ApiResponse.of(SuccessCode._OK, MemberMapper.toRefreshToken(newRefreshToken));
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        // Filter에서 작동하지만, Swagger 위해서 틀만 작성
        return ApiResponse.onSuccess(SuccessCode._OK);
    }
}
