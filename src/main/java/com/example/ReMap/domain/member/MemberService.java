package com.example.ReMap.domain.member;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.comment.Comment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    /* -------------------------- Read (조회) ----------------------------- */

    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Comment> getMemberComments(Long memberId) {
        return getMemberById(memberId).getComments();
    }

    @Transactional(readOnly = true)
    public List<Cast> getMemberCasts(Long memberId) {
        Member member = getMemberById(memberId);
        // USER도 자신의 캐스트(업로드)를 볼 수 있게 하려면 아래 검사를 제거하세요.
        if (member.getMemberType() != MemberType.RECORDER && member.getMemberType() != MemberType.ADMIN) {
            throw new IllegalArgumentException("기록자만 오디오 목록을 조회할 수 있습니다.");
        }
        return member.getCasts();
    }

    /* -------------------------- Update (수정) --------------------------- */

    public Member updateMember(Long id, UpdateMemberRequest dto) {
        Member member = getMemberById(id);

        // 닉네임 변경 시 중복 검사
        if (dto.getNickname() != null && !dto.getNickname().equals(member.getNickname())
                && memberRepository.existsByNickname(dto.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }
        member.setNickname(dto.getNickname());

        // 전화번호·생년월일 업데이트
        member.setPhone(dto.getPhone());
        member.setBirth(dto.getBirth());

        return member;
    }

    /* ----------------------- Role Self-Change -------------------------- */

    /**
     * 본인이 USER ↔ RECORDER 로만 스스로 타입 변경
     */
    public Member changeOwnRole(Long id, MemberType newType) {
        Member member = getMemberById(id);

        if (newType != MemberType.USER && newType != MemberType.RECORDER) {
            throw new IllegalArgumentException("변경 가능한 역할은 USER 또는 RECORDER 뿐입니다.");
        }

        member.setMemberType(newType);
        return member;
    }

    /* -------------------------- Delete (삭제) --------------------------- */

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("회원을 찾을 수 없습니다. ID: " + id);
        }
        memberRepository.deleteById(id);
    }
}
