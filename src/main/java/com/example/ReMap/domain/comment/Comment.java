package com.example.ReMap.domain.comment;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.member.entity.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cast_id")
    private Cast cast;
}

