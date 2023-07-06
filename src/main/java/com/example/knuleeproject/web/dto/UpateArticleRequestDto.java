package com.example.knuleeproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpateArticleRequestDto {
    private String title;
    private String content;
}
