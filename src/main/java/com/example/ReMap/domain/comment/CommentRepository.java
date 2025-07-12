package com.example.ReMap.domain.comment;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 댓글 레포지토리
 *
 * • findByCastIdOrderByCreatedAtAsc()  :   특정 Cast(게시글)의 댓글을
 *   작성 시간순으로 가져옵니다.
 * • @EntityGraph("member")            :   댓글과 작성자를 한 번에 로딩해서
 *   N+1 쿼리 문제를 예방합니다.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = "member")      // 댓글 조회 + Member 한 번에 가져오기
    List<Comment> findByCastIdOrderByCreatedAtAsc(Long castId);
}
