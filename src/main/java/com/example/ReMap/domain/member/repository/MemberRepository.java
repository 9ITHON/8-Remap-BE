package com.example.ReMap.domain.member.repository;

import com.example.ReMap.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    boolean existsByNickname(String nickname);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Member> findById(Long memberId);
}
