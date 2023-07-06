package com.example.knuleeproject.service;

import com.example.knuleeproject.config.jwt.TokenProvider;
import com.example.knuleeproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service

public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        // 토큰 유효성 검증에 실패하면 예외 발생.
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("unexpected token-유효성 검증 실패");
        }
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
