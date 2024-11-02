package com.k3c1.fitFood.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Entity
public class Member extends com.wondrous.board.domain.BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private String picture;

    @Builder
    public Member(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Member update(String picture) {
        this.picture = picture;
        return this;
    }

    //provider
    private String provider;

    //providerId
    private String providerId;

}
