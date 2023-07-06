package com.example.knuleeproject.repository;

import com.example.knuleeproject.domain.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(Long userId);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
