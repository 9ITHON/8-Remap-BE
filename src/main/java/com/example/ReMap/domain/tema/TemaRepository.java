package com.example.ReMap.domain.tema;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Tema(테마) 전용 레포지토리
 * - findByName   : 이름으로 단건 조회
 * - existsByName : 중복 이름 체크
 */
public interface TemaRepository extends JpaRepository<Tema, Long> {

    Optional<Tema> findByName(String name);

    boolean existsByName(String name);
}
