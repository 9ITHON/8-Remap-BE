package com.example.ReMap.domain.comment;


import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.comment.Comment;
import com.example.ReMap.domain.comment.CommentRepository;
import com.example.ReMap.domain.member.Member;
import com.example.ReMap.domain.member.MemberRepository;
import com.example.ReMap.domain.comment.dto.CommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor          // 의존성 주입용 생성자 자동
@Transactional                    // 메서드 기본 read-write
public class CommentService {

    private final CommentRepository commentRepo;
    private final com.example.ReMap.domain.cast.CastRepository castRepo;
    private final MemberRepository  memberRepo;

    /* ---------- CREATE ---------- */

    public CommentResponse create(Long castId, Long memberId, String content) {

        Cast cast = castRepo.findById(castId)
                .orElseThrow(() -> new IllegalArgumentException("Cast not found : " + castId));

        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found : " + memberId));

        // 댓글 엔티티 생성
        Comment comment = Comment.builder()
                .content(content)
                .build();

        // FK 세팅 (엔티티 @Setter 덕분에 가능)
        comment.setCast(cast);
        comment.setMember(member);

        // 저장
        commentRepo.save(comment);

        return CommentResponse.from(comment);
    }

    /* ---------- READ (목록) ---------- */
    @Transactional(Transactional.TxType.SUPPORTS)   // read-only 트랜잭션
    public List<CommentResponse> list(Long castId) {
        return commentRepo.findByCastIdOrderByCreatedAtAsc(castId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    /* ---------- UPDATE ---------- */

    public CommentResponse update(Long commentId, Long requesterId, String newContent) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found : " + commentId));

        // 작성자 권한 확인
        if (!requesterId.equals(comment.getMember() != null ? comment.getMember().getId() : null)) {
            throw new AccessDeniedException("본인 댓글만 수정할 수 있습니다.");
        }

        comment.changeContent(newContent);   // 엔티티 내부에서 필드 변경

        return CommentResponse.from(comment);
    }

    /* ---------- DELETE ---------- */

    public void delete(Long commentId, Long requesterId) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found : " + commentId));

        if (!requesterId.equals(comment.getMember() != null ? comment.getMember().getId() : null)) {
            throw new AccessDeniedException("본인 댓글만 삭제할 수 있습니다.");
        }

        commentRepo.delete(comment);          // 하드 삭제
        // → 소프트 삭제가 필요하면 Comment에 deleted 필드를 추가해 플래그만 변경
    }
}
