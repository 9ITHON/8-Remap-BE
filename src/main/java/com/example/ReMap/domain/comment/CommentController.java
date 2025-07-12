package com.example.ReMap.web.comment;

import com.example.ReMap.domain.comment.CommentService;
import com.example.ReMap.domain.comment.dto.CommentResponse;
import com.example.ReMap.domain.comment.dto.CreateCommentRequest;
import com.example.ReMap.domain.comment.dto.UpdateCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/casts/{castId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /* ------------------------------------ 1. 댓글 등록 ------------------------------------ */
    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long castId,
            @RequestBody @Valid CreateCommentRequest req,
            Principal principal            // 로그인 사용자 정보(아이디 문자열)
    ) {
        Long memberId = Long.valueOf(principal.getName());   // "1" → 1L 등으로 변환
        CommentResponse resp = commentService.create(castId, memberId, req.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    /* ------------------------------------ 2. 댓글 목록 ------------------------------------ */
    @GetMapping
    public List<CommentResponse> list(@PathVariable Long castId) {
        return commentService.list(castId);
    }

    /* ------------------------------------ 3. 댓글 수정 ------------------------------------ */
    @PutMapping("/{commentId}")
    public CommentResponse update(
            @PathVariable Long castId,      // 경로 일관성 확보용
            @PathVariable Long commentId,
            @RequestBody @Valid UpdateCommentRequest req,
            Principal principal
    ) {
        Long memberId = Long.valueOf(principal.getName());
        return commentService.update(commentId, memberId, req.getContent());
    }

    /* ------------------------------------ 4. 댓글 삭제 ------------------------------------ */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long castId,
            @PathVariable Long commentId,
            Principal principal
    ) {
        Long memberId = Long.valueOf(principal.getName());
        commentService.delete(commentId, memberId);
        return ResponseEntity.noContent().build();
    }
}
