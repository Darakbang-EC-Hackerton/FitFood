package com.k3c1.fitFood.domain.board.entity;

import com.k3c1.fitFood.domain.BaseTimeEntity;
import com.k3c1.fitFood.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DietArticle extends BaseTimeEntity { // Updated to use the correct import
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

  
  
  
  
    @OneToMany(mappedBy = "dietArticle", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "dietArticle", cascade = CascadeType.REMOVE)
    private List<ArticleLike> likes = new ArrayList<>();

    @Builder
    public DietArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {;
        this.title = title;
        this.content = content;
    }
}