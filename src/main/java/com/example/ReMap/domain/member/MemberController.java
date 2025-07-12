package com.example.ReMap.domain.member;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.comment.Comment;
import com.example.ReMap.domain.auth.util.JwtUtil;  // 실제 JwtUtil 위치에 맞춰 수정
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    /** 내 정보 조회 **/
    @GetMapping("/setting")
    public ResponseEntity<Member> getMember(
            @RequestHeader("Authorization") String authorization) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    /** 내 정보 수정 **/
    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(
            @RequestHeader("Authorization") String authorization,
            @Valid @RequestBody UpdateMemberRequest req) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        return ResponseEntity.ok(memberService.updateMember(memberId, req));
    }

    /** 비밀번호 변경 **/
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(
            @RequestHeader("Authorization") String authorization,
            @Valid @RequestBody ChangePasswordRequest req) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        memberService.changePassword(memberId, req);
        return ResponseEntity.ok().build();
    }

    /** 내가 쓴 댓글 목록 **/
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments(
            @RequestHeader("Authorization") String authorization) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        return ResponseEntity.ok(memberService.getMemberComments(memberId));
    }

    /** 내가 올린 오디오 목록 **/
    @GetMapping("/casts")
    public ResponseEntity<List<Cast>> getCasts(
            @RequestHeader("Authorization") String authorization) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        return ResponseEntity.ok(memberService.getMemberCasts(memberId));
    }

    /** USER ↔ RECORDER 역할 전환 **/
    @PutMapping("/member-type")
    public ResponseEntity<Member> changeRole(
            @RequestHeader("Authorization") String authorization,
            @Valid @RequestBody ChangeMemberTypeRequest req) {
        Long memberId = jwtUtil.extractMemberId(authorization);
        return ResponseEntity.ok(
                memberService.changeOwnRole(memberId, req.getMemberType())
        );
    }
}
