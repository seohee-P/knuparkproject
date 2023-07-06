package com.example.knuleeproject.web;

import com.example.knuleeproject.service.TokenService;
import com.example.knuleeproject.web.dto.CreateAccessTokenRequestDto;
import com.example.knuleeproject.web.dto.CreateAccessTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/latest/token")
    public ResponseEntity<CreateAccessTokenResponseDto> createNewAccessToken(@RequestBody CreateAccessTokenRequestDto createAccessTokenRequestDto){
        String newAccessToken = tokenService.createNewAccessToken(createAccessTokenRequestDto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDto(newAccessToken));
    }
}
