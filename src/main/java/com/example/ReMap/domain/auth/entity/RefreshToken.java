package com.example.ReMap.domain.auth.entity;


import com.example.ReMap.domain.member.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 기본 생성자
    public RefreshToken() {}

    // 생성자
    public RefreshToken(Member member, String refreshToken, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.member = member;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
