package com.example.knuparkproject.web.dto;

import lombok.Getter;

@Getter
public class CreateAccessTokenResponseDto {

    private String accessToken;

    public CreateAccessTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
