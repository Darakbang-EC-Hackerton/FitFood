package com.k3c1.fitFood.domain.board.apicontroller;

import com.k3c1.fitFood.domain.board.dto.ArticleRequest;
import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import com.k3c1.fitFood.domain.board.service.DietBoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardApiController {
    private final DietBoardService BoardService;

    @GetMapping("/api/articles")
    public String articles(Model model) {
        List<ArticleRequest> articles = BoardService.findAll()
                .stream()
                .map(ArticleRequest::new)
                .toList();
        model.addAttribute("articles", articles);
        return "/board/articles";
    }

    @GetMapping("/api/articles/${id}")
    public String article(@PathVariable Long id, Model model) {
        DietArticle article = BoardService.findById(id);
        model.addAttribute("article", new ArticleRequest(article));
        return "/board/article";
    }

    @GetMapping("/api/articles/${content}")
    public String article(Model model, @PathVariable String id) {

    }

    @PostMapping("/api/article/${title}")

    @PutMapping("/api/article/${id}")
    public String updateArticle(@PathVariable String title, Model model, @PathVariable Long id) {}

    @DeleteMapping("/api/article/${id}")


}
