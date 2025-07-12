package com.example.ReMap.domain.comment;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter                              // 전체 Setter 허용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** VARCHAR(255) 에 맞춤 */
    @Column(length = 255, nullable = false)
    private String content;

    /** 생성 시각 – DB 기본값 대신 애플리케이션에서 채움 */
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /* ---------- 연관 관계 ---------- */

    /** 작성자 – DDL이 NULL 허용 → optional = true */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    /** 대상 캐스트 – DDL이 NULL 허용 → optional = true */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cast_id")
    private Cast cast;

    /* ---------- 도메인 메서드 ---------- */

    public void changeContent(String newContent) {
        this.content = newContent;
    }
}
