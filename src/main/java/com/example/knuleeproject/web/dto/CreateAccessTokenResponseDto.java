package com.example.knuleeproject.web.dto;

import lombok.Getter;

@Getter
public class CreateAccessTokenResponseDto {

    private String accessToken;

    public CreateAccessTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
