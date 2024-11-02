package com.k3c1.fitFood.domain.board.entity;

import com.k3c1.fitFood.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ArticleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "diet_article_id")
    private DietArticle dietArticle;

    @Column(nullable = false)
    private int count;

    public ArticleLike(DietArticle article, Member member) {
    }
}
