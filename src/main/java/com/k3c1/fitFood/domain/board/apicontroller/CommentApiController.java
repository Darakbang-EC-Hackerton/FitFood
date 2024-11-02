package com.k3c1.fitFood.domain.board.apicontroller;

import com.k3c1.fitFood.domain.board.dto.CommentViewRequest;
import com.k3c1.fitFood.domain.board.dto.CommentViewResponse;
import com.k3c1.fitFood.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    //게시글 등록
    @PostMapping("/comment")
    ResponseEntity<?> createComment(@RequestBody CommentViewRequest commentViewRequest) {
        CommentViewResponse responseDto = commentService.createComment(commentViewRequest);
        return responseDto != null
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.badRequest().body("댓글을 등록할 수 없습니다.");
    }

    //모든 댓글 조회
    @GetMapping("/comments/{id}")
    ResponseEntity<?> findAllComments(@PathVariable Long parentId) {
        List<CommentViewResponse> comments = commentService.getComments(parentId);

        return (comments != null && !comments.isEmpty())
            ? ResponseEntity.ok(comments)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 조회할 수 없습니다");
    }

    //댓글 수정
    @PutMapping("/comment/{id}")
    ResponseEntity<?> updateComment(@RequestBody CommentViewRequest commentViewRequest) {
        CommentViewResponse responseDto = commentService.updateComment(commentViewRequest);
        if (commentViewRequest == null) {
            return null;
        }
        return (responseDto != null)
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글을 수정할 수 없습니다");
    }
    // 게시글 삭제
    @DeleteMapping("/comment")
    ResponseEntity<?> deleteComment(@RequestBody CommentViewRequest commentViewRequest) {
        CommentViewResponse responseDto = commentService.deleteComment(commentViewRequest);
        return responseDto == null
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글을 삭제할 수 없습니다.");
    }

}
