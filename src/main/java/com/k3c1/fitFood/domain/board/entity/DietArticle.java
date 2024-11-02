package com.k3c1.fitFood.domain.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class DietArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Builder
    public DietArticle(String title, String content, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.phoneNumber = phoneNumber;
    }

    public void uppdate()
}
