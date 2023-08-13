package com.example.knuparkproject.service;

import com.example.knuparkproject.domain.user.User;
import com.example.knuparkproject.repository.UserRepository;
import com.example.knuparkproject.web.dto.AddUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 레퍼지토리 호출해야 해서
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequestDto requestDto){
        return userRepository.save(
                User.builder()
                        .email(requestDto.getEmail())
                        .password(bCryptPasswordEncoder.encode(requestDto.getPassword())) //password 인코딩
                        .build()
        ).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("unexpected user")); // 없다면 오류처리.
    }
}
