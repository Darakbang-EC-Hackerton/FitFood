package com.k3c1.fitFood.domain.board.repository;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietArticleRepository extends JpaRepository<DietArticle, Long> {
    public List<DietArticle> findByTitleContainingOrContentContaining(String title, String content);
    public List<DietArticle> findByContent(String content);

}
