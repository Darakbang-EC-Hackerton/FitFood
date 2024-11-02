package com.k3c1.fitFood.domain.board.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentViewRequest {
    private Long id;
    private String comment;
    private Long memberId;
    private Long dietArticleId;
}
