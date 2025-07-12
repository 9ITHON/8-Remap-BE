package com.example.ReMap.domain.member.repository;

import com.example.ReMap.domain.member.entity.Refresh;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    Boolean existsByRefreshToken(String refreshToken);
    Optional<RedisProperties.Lettuce.Cluster.Refresh> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
