package com.example.knuleeproject.config.jwt;

import com.example.knuleeproject.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();

        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    private String makeToken(Date expiry, User user){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입 : JWT
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now) // 현재 시각
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id",user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact(); //build 안쓰고
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
        // 토큰 기반으로 인증 정보를 가져오는 작업
        //
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        //
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                    claims.getSubject(), "",authorities
            ), // principal
            token,
            authorities
        );
    }

    public Long getUserId(String token){
        Claims claims = getClaims(token);

        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey((jwtProperties.getSecret()))
                .parseClaimsJws(token)
                .getBody();
    }
}
