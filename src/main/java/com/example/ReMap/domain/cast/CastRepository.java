package com.example.ReMap.domain.cast;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Cast(게시글) 전용 JPA 레포지토리
 * → JpaRepository 를 상속하면 findById, save, delete 등 기본 메서드가 자동 생성됩니다.
 */
public interface CastRepository extends JpaRepository<Cast, Long> {
    // 필요하면 여기에 커스텀 쿼리 메서드 추가
}
