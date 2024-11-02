package com.k3c1.fitFood.domain.board.dto;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import lombok.Builder;

// 뷰에서 사용할 dto
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private String author;


    public ArticleViewResponse(DietArticle article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

    @Builder
    public ArticleViewResponse(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}