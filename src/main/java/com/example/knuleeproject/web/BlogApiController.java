package com.example.knuleeproject.web;


import com.example.knuleeproject.domain.blog.Article;
import com.example.knuleeproject.service.BlogService;
import com.example.knuleeproject.web.dto.AddArticleRequest;
import com.example.knuleeproject.web.dto.ArticleResponseDto;
import com.example.knuleeproject.web.dto.UpateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/lastest/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest requestDto){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(blogService.save(requestDto));
    }

    @GetMapping("/api/lastest/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
        List<ArticleResponseDto>  articles = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(articles);

    }

    @GetMapping("/api/lastest/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findArticle(@PathVariable Long id){
        return ResponseEntity.ok().body(new ArticleResponseDto(blogService.findByid(id)));
    }

    @DeleteMapping("api/lastest/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();

    }

    //수정
    @PutMapping("/api/lastest/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpateArticleRequestDto requestDto) {
        Article updatedArticle = blogService.update(id,requestDto);

        return ResponseEntity.ok().body(updatedArticle);

    }

}
