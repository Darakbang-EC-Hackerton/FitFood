package com.k3c1.fitFood.domain.board.entity;

import com.k3c1.fitFood.domain.BaseTimeEntity;
import com.k3c1.fitFood.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter

@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "diet_article_id")
    private DietArticle dietArticle;

    @ManyToOne
    @JoinColumn(name = "member")
    private Member member;

    @Builder
    public Comment(String comment, DietArticle dietArticle, Member member) {
        this.comment = comment;
        this.dietArticle = dietArticle;
        this.member = member;
    }

    public void update(String comment) {
        this.comment = comment;
    }


}
