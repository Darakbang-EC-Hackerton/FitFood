package com.k3c1.fitFood.domain.board.apicontroller;

import com.k3c1.fitFood.domain.board.dto.ArticleViewRequest;
import com.k3c1.fitFood.domain.board.dto.ArticleViewResponse;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.service.DietBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/articles")  // 공통 URL prefix 설정
public class BoardApiController {

    private final DietBoardService boardService;

    // 모든 게시글 조회
    @GetMapping
    public String articles(Model model) {
        List<ArticleViewRequest> articles = boardService.findAll()
                .stream()
                .map(ArticleViewRequest::new)
                .toList();
        model.addAttribute("articles", articles);
        return "/board/articles";
    }

    // 특정 게시글 조회 (ID 기준)
    @GetMapping("/{id}")
    public String article(@PathVariable Long id, Model model) {
        DietArticle article = boardService.findById(id);
        //
        model.addAttribute("article", new ArticleViewResponse(article));
        return "/board/article";
    }

    // 특정 게시글 조회 (내용 기준)
    @GetMapping("/content/{content}")
    public String articleByContent(@PathVariable String content, Model model) {
        List<ArticleViewRequest> articles = boardService.findByContent(content)
                .stream()
                .map(ArticleViewRequest::new)
                .toList();
        model.addAttribute("articles", articles);
        return "/board/articles";
    }

    // 새로운 게시글 추가
    @PostMapping
    public String createArticle(@RequestBody ArticleViewRequest articleRequest, Model model) {
        DietArticle savedArticle = boardService.save(articleRequest.toEntity());
        model.addAttribute("article", new ArticleViewRequest(savedArticle));
        return "redirect:/api/articles";
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleViewRequest articleRequest, Model model) {
        DietArticle updatedArticle = boardService.update(id, articleRequest.toEntity());
        model.addAttribute("article", new ArticleViewRequest(updatedArticle));
        return "redirect:/api/articles/" + id;
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/api/articles";
    }
}
