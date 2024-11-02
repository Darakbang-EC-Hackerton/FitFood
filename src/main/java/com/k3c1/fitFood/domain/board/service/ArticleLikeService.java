package com.k3c1.fitFood.domain.board.service;

import com.k3c1.fitFood.domain.board.entity.ArticleLike;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.ArticleLikeRepository;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import com.k3c1.fitFood.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final DietArticleRepository dietArticleRepository;


    public Optional<ArticleLike> articleLike(Long articleId, Long memberId) {
        return articleLikeRepository.findByDietArticleIdAndMemberId(articleId, memberId);
    }



    //유저가 좋아요한지 안한지 확인한 후 증가, 감소시켜주는 메서드
    public Boolean likeArticle(Long articleId, Member member) {
        Optional<ArticleLike> like = articleLikeRepository.findByDietArticleIdAndMemberId(articleId, member.getId());

        if (like.isEmpty()) {
            DietArticle article = findArticle(articleId);
            CheckMember(article, member);
            articleLikeRepository.save(new ArticleLike(article, member));
            return true;
        }
        else {
            articleLikeRepository.delete(like.get());
            return false;
        }
    }

    private void CheckMember(DietArticle article, Member member) {
        if (!article.getMember().equals(member)) {
            throw new IllegalArgumentException("User not authorized to like this article");
        }
    }

    private DietArticle findArticle(Long articleId) {
        return dietArticleRepository.findById(articleId).orElse(null);
    }



}
