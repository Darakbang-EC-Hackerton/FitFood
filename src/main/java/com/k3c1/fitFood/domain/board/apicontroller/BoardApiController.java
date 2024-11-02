package com.k3c1.fitFood.domain.board.apicontroller;

import com.k3c1.fitFood.domain.board.dto.ArticleViewRequest;
import com.k3c1.fitFood.domain.board.dto.ArticleViewResponse;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.service.DietBoardService;
import jakarta.transaction.Transactional;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")  // 공통 URL prefix 설정
public class BoardApiController {

    private final DietBoardService boardService;

    // 모든 게시글 조회 (숙제)
    @GetMapping
    public ResponseEntity<?> find1All() {
        List<ArticleViewResponse> responseDtos = boardService.findAll();

        return (responseDtos != null && !responseDtos.isEmpty())
                ? ResponseEntity.ok(responseDtos)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 없습니다.");
    }


    // 특정 게시글 조회 (ID 기준)
    @GetMapping("/{id}")
    public ResponseEntity<?> article(@PathVariable Long id, Model model) {
        ArticleViewResponse responseDto = boardService.findById(id);
        return (responseDto != null)
                ? ResponseEntity.ok(responseDto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 없습니다.");
    }

    // 특정 게시글 조회 (내용 기준)
    @GetMapping("/content/{content}")
    public ResponseEntity<?> articleByContent(@PathVariable String content) {
        List<ArticleViewResponse> dtos = boardService.findByContent(content);

        return dtos != null
                ? ResponseEntity.ok(dtos)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글이 없습니다.");

    }


    // 새로운 게시글 추가
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleViewRequest articleRequest) {
        Long save = boardService.create(articleRequest);

        return save != null
                ? ResponseEntity.ok().body(save + "의 게시글이 저장되었습니다.")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("저장할 수 없습니다.");

    }

    // 게시글 수정
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody ArticleViewRequest articleRequest) {
        Long updated = boardService.update(articleRequest, id);


        return updated != null
                ? ResponseEntity.ok().body(updated + "의 게시글이 수정되었습니다.")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정할 게시글이 없습니다.");
    }

    // 게시글 삭제
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        Long deleted = boardService.delete(id);

        return deleted != null
                ? ResponseEntity.ok().body(deleted + "의 게시글이 삭제되었습니다.")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 게시글이 없습니다.");
    }
}
