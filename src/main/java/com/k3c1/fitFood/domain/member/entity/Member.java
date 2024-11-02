package com.k3c1.fitFood.domain.member.entity;

import com.k3c1.fitFood.domain.BaseTimeEntity;
import com.k3c1.fitFood.domain.board.entity.Comment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Entity
@Getter
public class Member extends BaseTimeEntity {
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


    @Column
    private String role;

    
  
  
  
  @OneToMany(mappedBy = "Member", cascade = CascadeType.REMOVE)
    List<Comment> commentList = new ArrayList<>();


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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    //provider
    private String provider;

    //providerId
    private String providerId;

}
