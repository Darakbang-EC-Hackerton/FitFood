package com.k3c1.fitFood.domain.board.dto;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.zaxxer.hikari.util.UtilityElf;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ArticleViewRequest {

    private String title;
    private String content;
    private String author;


}
