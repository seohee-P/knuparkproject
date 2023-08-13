package com.example.knuparkproject.service;

import com.example.knuparkproject.domain.blog.Article;
import com.example.knuparkproject.repository.BlogRepository;
import com.example.knuparkproject.web.dto.AddArticleRequest;
import com.example.knuparkproject.web.dto.UpateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // 생성자를 대신해준다.
@Service
public class BlogService {
    private final BlogRepository blogRepository;


    //save
    public Article save(AddArticleRequest requestDto){
        return blogRepository.save(requestDto.toEntity());
    }

    //read - 전체 조회
    public List<Article> findAll() {
        return blogRepository.findAll();

    }
    // 단건 조회
    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("article not exist ! : " + id));

    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    // 수정하는 코드를 넣으면 된다.
    @Transactional // 세부작업들의 모음집 한 세트로 모두 실행 완료 되어야 처리됨을 의마한다.
                    // step 1. 2.가 모두 실행 되어야 작업이 끝남.
    public Article update(Long id, UpateArticleRequestDto requestDto) {
        // step 1. 기존 등록된 글을 가져옴
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not exist! : " + id));
        // 지금 이 메서드가 없데이트를 하는 메서드이므로
        // 서비스 단에서 조회된 데이터를 업데이트 하는 코드가 나오면 된다.

        // step 2. 원하는 제목과 내용을 수정함
        article.update(requestDto.getTitle(), requestDto.getContent());
        // 그런데 이상하네.. ?? 업데이트될 객테는 만들었는데..
        // 이걸 리퍼지토리로 넘기는 무언가가 없네?
        // 동작이 된다 JPA에 영속성 특성에 의해
        return  article;
    }
}

