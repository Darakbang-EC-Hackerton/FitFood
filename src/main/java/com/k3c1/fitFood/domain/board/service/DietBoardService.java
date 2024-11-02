package com.k3c1.fitFood.domain.board.service;

import com.k3c1.fitFood.domain.board.dto.ArticleViewRequest;
import com.k3c1.fitFood.domain.board.dto.ArticleViewResponse;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietBoardService {
    DietArticleRepository articleRepository;

    public List<ArticleViewResponse> findAll() {
       return articleRepository.findAll()
               .stream()
               .map(ArticleViewResponse::new)
               .toList();
    }

    public ArticleViewResponse findById(Long id) {
        DietArticle article = articleRepository.findById(id).orElse(null);
        return new ArticleViewResponse(article);
    }


    public List<ArticleViewResponse> findByTitle(String title) {
        List<DietArticle> foundArticles = articleRepository.findByContent(title);
        return foundArticles.stream()
                .map(ArticleViewResponse::new)
                .toList();
    }


    public List<ArticleViewResponse> findByContent(String content) {
        List<DietArticle> foundArticles = articleRepository.findByContent(content);
        return foundArticles.stream()
                .map(ArticleViewResponse::new)
                .toList();
    }


    // 제목 또는 내용으로 게시글 조회
    public List<ArticleViewResponse> findByTitleOrContent(String title, String content) {
        // Retrieve articles that contain the specified title or content
        List<DietArticle> foundArticles = articleRepository.findByTitleContainingOrContentContaining(title, content);

        // Map the retrieved articles to ArticleViewResponse objects
        return foundArticles.stream()
                .map(ArticleViewResponse::new) // Assuming ArticleViewResponse has a constructor that accepts a DietArticle
                .toList();
    }

    @Transactional
    public Long create(ArticleViewRequest articleViewRequest) {
        // Build the DietArticle entity using the request data
        DietArticle entity = DietArticle.builder()
                .title(articleViewRequest.getTitle()) // Assuming getTitle() is a method in ArticleViewRequest
                .content(articleViewRequest.getContent()) // Assuming getContent() is a method in ArticleViewRequest
                .build();

        // Save the entity and return its ID
        return articleRepository.save(entity).getId();
    }

    @Transactional
    public Long update(ArticleViewRequest articleViewRequest, @PathVariable Long id) {
        // request는 맵핑 지금 게시물을
        DietArticle targetEntity = articleRepository.findById(id).orElse(null);
        if (articleViewRequest.getTitle() == null) {
            return null;
        }
        targetEntity.update(articleViewRequest.getTitle(), articleViewRequest.getContent());
        return articleRepository.save(targetEntity).getId();
    }

    @Transactional
    public Long delete(Long id) {
        DietArticle targetEntity = articleRepository.findById(id).orElse(null);
        if (targetEntity == null) {
            return null;
        }
        articleRepository.delete(targetEntity);
        return targetEntity.getId();
    }




}
