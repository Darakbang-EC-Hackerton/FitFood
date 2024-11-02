package com.k3c1.fitFood.domain.board.service;

import com.k3c1.fitFood.domain.board.dto.CommentViewRequest;
import com.k3c1.fitFood.domain.board.dto.CommentViewResponse;
import com.k3c1.fitFood.domain.board.entity.Comment;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.CommentRepository;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import com.k3c1.fitFood.domain.member.entity.Member;
import com.k3c1.fitFood.domain.member.repository.MemberRepository;
import com.k3c1.fitFood.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentService {
    private final DietArticleRepository dietArticleRepository;
    private final CommentRepository commentRepository;
    public final MemberRepository memberRepository;

    //댓글 등록
    public CommentViewResponse createCOmment(CommentViewRequest commentViewRequest) {
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




}
