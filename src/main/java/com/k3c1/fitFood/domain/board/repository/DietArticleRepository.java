package com.k3c1.fitFood.domain.board.repository;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietArticleRepository extends JpaRepository<DietArticle, Long> {
}
