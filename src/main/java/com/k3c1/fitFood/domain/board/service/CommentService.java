package com.k3c1.fitFood.domain.board.service;

import com.k3c1.fitFood.domain.board.dto.CommentViewRequest;
import com.k3c1.fitFood.domain.board.dto.CommentViewResponse;
import com.k3c1.fitFood.domain.board.entity.Comment;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.CommentRepository;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import com.k3c1.fitFood.domain.member.entity.Member;
import com.k3c1.fitFood.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CommentService {
    private final DietArticleRepository dietArticleRepository;
    private final CommentRepository commentRepository;
    public final MemberRepository memberRepository;

    //댓글 등록
    public CommentViewResponse createComment(CommentViewRequest commentViewRequest) {
        DietArticle parentArticle = dietArticleRepository.findById(commentViewRequest.getDietArticleId()).orElse(null);
        Member parentMember = memberRepository.findById(commentViewRequest.getMemberId()).orElse(null);
        if(parentArticle == null || parentMember == null) {
            return null;
        }

        Comment createComment = Comment.builder()
                .member(parentMember)
                .dietArticle(parentArticle)
                .comment(commentViewRequest.getComment()).build();

        Comment saved = commentRepository.save(createComment);

        return new CommentViewResponse(saved);
    }


    //댓글 목록 가져오기
    public List<CommentViewResponse> getComments(Long articleId) {
        dietArticleRepository.findById(articleId).orElse(null);
        if(articleId == null) {
            return null;
        }
        List<Comment> comments = commentRepository.findByDietArticleId(articleId);
        return comments.stream()
                .map(CommentViewResponse ::new)
                .toList();
    }

    //댓글 수정
    public CommentViewResponse updateComment(CommentViewRequest commentViewRequest) {
        DietArticle parentArticle = dietArticleRepository.findById(commentViewRequest.getDietArticleId()).orElse(null);
        Member parentMember = memberRepository.findById(commentViewRequest.getMemberId()).orElse(null);
        if(parentArticle == null || parentMember == null) {
            return null;
        }
        Comment target = commentRepository.findById(commentViewRequest.getId()).orElse(null);
        if(target == null) {
            return null;
        }
        //멤버의 권한 확인 후 로그인 상태이면 진행
        target.update(commentViewRequest.getComment());
        Comment updated = commentRepository.save(target);
        return new CommentViewResponse(updated);
    }

    //댓글 삭제
    public CommentViewResponse deleteComment(CommentViewRequest commentViewRequest) {
        DietArticle parentArticle = dietArticleRepository.findById(commentViewRequest.getDietArticleId()).orElse(null);
        Member parentMember = memberRepository.findById(commentViewRequest.getMemberId()).orElse(null);
        if(parentArticle == null || parentMember == null) {
            return null;
        }

        Comment target = commentRepository.findById(commentViewRequest.getId()).orElse(null);
        if(target == null) {
            return null;
        }
        //멤버의 role 검사 후 로그인 상태이면 진행
        commentRepository.delete(target);
        return new CommentViewResponse(target);
    }
}
