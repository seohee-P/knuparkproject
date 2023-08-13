package com.example.knuparkproject.web.dto;

import com.example.knuparkproject.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    private String title;
    private String content;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
