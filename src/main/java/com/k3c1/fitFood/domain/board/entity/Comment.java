package com.k3c1.fitFood.domain.board.entity;

import com.k3c1.fitFood.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity@Getter@NoArgsConstructor(access = AccessLevel.PROTECTED)public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conmment", nullable = false)
    private String comment;

    @ManyToOne

    @JoinColumn(name = "member_id")
    private Member member;

  
  
  
  
  
  
  
  
  
  
  
    @JoinColumn(name = "diet_article_id", nullable = false)
    private DietArticle dietArticle; // This should match the mappedBy in DietArticle
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // This should match the mappedBy in DietArticle


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