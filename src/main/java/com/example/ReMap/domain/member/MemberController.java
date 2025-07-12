package com.example.ReMap.domain.member;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.comment.Comment;
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

    /** 1) 내 정보 조회 **/
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    /** 2) 내 정보 수정 **/
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberRequest req) {
        return ResponseEntity.ok(memberService.updateMember(id, req));
    }

    /** 3) 회원 탈퇴 **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    /** 4) 내가 쓴 감상후기 목록 **/
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getMyComments(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberComments(id));
    }

    /** 5) 내가 올린 오디오 목록 **/
    @GetMapping("/{id}/casts")
    public ResponseEntity<List<Cast>> getMyCasts(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberCasts(id));
    }

    /** 6) USER ↔ RECORDER 역할 전환 **/
    @PutMapping("/{id}/member-type")
    public ResponseEntity<Member> changeOwnRole(
            @PathVariable Long id,
            @Valid @RequestBody ChangeMemberTypeRequest req) {
        return ResponseEntity.ok(
                memberService.changeOwnRole(id, req.getMemberType())
        );
    }
}
