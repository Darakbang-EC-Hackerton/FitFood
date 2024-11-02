package com.k3c1.fitFood.domain.board.repository;

import com.k3c1.fitFood.domain.board.entity.ArticleLike;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    Optional<ArticleLike> findByDietArticleIdAndMemberId(Long dietArticleId, Long memberId);

}
