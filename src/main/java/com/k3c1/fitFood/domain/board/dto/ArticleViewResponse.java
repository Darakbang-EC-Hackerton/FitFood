package com.k3c1.fitFood.domain.board.dto;

import com.k3c1.fitFood.domain.board.entity.DietArticle;

// 뷰에서 사용할 dto
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleViewResponse(DietArticle article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
