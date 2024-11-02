package com.k3c1.fitFood.domain.board.dto;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticleRequest {
    private Long id;
    private String title;
    private String content;

    public ArticleRequest(DietArticle dietArticle) {
       this.id = dietArticle.getId();
       this.title = dietArticle.getTitle();
       this.content = dietArticle.getContent();
    }
}
