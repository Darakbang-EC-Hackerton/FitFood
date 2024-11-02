package com.k3c1.fitFood.domain.board.repository;

import com.k3c1.fitFood.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByDietArticleId(Long articleId);
}
