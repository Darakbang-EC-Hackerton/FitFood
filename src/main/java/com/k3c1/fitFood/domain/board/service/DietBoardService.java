package com.k3c1.fitFood.domain.board.service;

import com.k3c1.fitFood.domain.board.entity.DietArticle;
import com.k3c1.fitFood.domain.board.repository.DietArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietBoardService {
    DietArticleRepository ArticleRepository;

    public List<DietArticle> findAll() {
       return ArticleRepository.findAll();
    }

    public DietArticle findById(Long id) {
       return ArticleRepository.findById(id);
    }


    public Collection<Object> findByContent(String content) {

    }
}
