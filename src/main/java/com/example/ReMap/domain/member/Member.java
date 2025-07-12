package com.example.ReMap.domain.member;

import com.example.ReMap.domain.cast.Cast;
import com.example.ReMap.domain.comment.Comment;
import com.example.ReMap.domain.auth.entity.RefreshToken;
import com.example.ReMap.domain.member.MemberType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    private Integer phone;

    private Long birth;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "memberType")
    private MemberType memberType = MemberType.GENERAL;

    // 관계 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cast> casts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    }
