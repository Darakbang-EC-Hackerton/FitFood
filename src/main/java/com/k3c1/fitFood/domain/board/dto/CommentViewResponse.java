package com.k3c1.fitFood.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.k3c1.fitFood.domain.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentViewResponse {
    private Long id;
    private String comment;
    private Long memberId;
    private Long dietArticleId;

    public CommentViewResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.memberId = comment.getMember().getId();
        this.dietArticleId = comment.getDietArticle().getId();
    }
}




