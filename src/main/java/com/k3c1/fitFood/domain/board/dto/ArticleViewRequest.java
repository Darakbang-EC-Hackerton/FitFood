package com.k3c1.fitFood.domain.board.dto;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleViewRequest {

    private Long id;
    private String title;
    private String content;

    public ArticleViewRequest(DietArticle article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
