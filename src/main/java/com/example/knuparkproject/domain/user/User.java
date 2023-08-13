package com.example.knuparkproject.domain.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    // userDetails<<interface>> 오버라이딩 메소드
    @Override
    // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    // 사용자의 아이디 반환
    public String getUsername() {
        return email; // 이메일 반환
    }

    @Override
    // 계정이 만료 여부 반환
    public boolean isAccountNonExpired() {
        // 만료되었지 확인하는 로직. true - > 만료되지 않음
        return true;
    }

    @Override
    // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 비밀번호 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true;
    }
}
