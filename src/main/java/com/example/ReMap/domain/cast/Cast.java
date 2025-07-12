package com.example.ReMap.domain.cast;

import com.example.ReMap.domain.member.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`cast`")  // 예약어 주의!
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 생성자, getter, setter
}
