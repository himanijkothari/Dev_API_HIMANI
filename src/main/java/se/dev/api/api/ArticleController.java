/**
 * Represents the controller class for Article entity
 * that manages the request and response
 */

package se.dev.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dev.api.repository.ArticleRepository;
import se.dev.api.api.exception.ResourceNotFoundException;
import se.dev.api.model.Article;
import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> listAllArticle()
    {
        List<Article> article = articleRepository.findAll();
        return article;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id)
    {
        Article article = articleRepository
                            .findById(id)
                            .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article)
    {
        articleRepository.save(article);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(article);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle)
    {
        articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return  ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long id)
    {
        Article article = articleRepository
                            .findById(id)
                            .orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
    }
}
