/**
 * Represents the controller class for Comment entity
 * that manages the request and response
 */

package se.dev.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dev.api.api.exception.ResourceNotFoundException;
import se.dev.api.model.Article;
import se.dev.api.model.Comment;
import se.dev.api.repository.ArticleRepository;
import se.dev.api.repository.CommentRepository;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository,ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<Comment>> listOfAllCommentOnArticle(@PathVariable Long articleId)
    {
        Article article = articleRepository
                            .findById(articleId)
                            .orElseThrow(ResourceNotFoundException::new);
        List<Comment> comments = article.getComments();
        return ResponseEntity.ok(comments);
    }


   @GetMapping("/comments")
    public ResponseEntity<List<Comment>> listAllCommentByAuthor(@RequestParam(value ="authorName", required = true) String authorName)
    {
        List<Comment> comments = commentRepository
                                    .findByAuthorName(authorName)
                                    .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(comments);
    }


    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long articleId, @RequestBody Comment comment)
    {
        Article article = articleRepository
                            .findById(articleId)
                            .orElseThrow(ResourceNotFoundException::new);
        comment.setArticle(article);
        commentRepository.save(comment);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }


    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,@Valid @RequestBody Comment updatedComment)
    {
        Comment comment = commentRepository
                            .findById(id)
                            .orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return ResponseEntity.ok(updatedComment);
    }


    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id)
    {
        Comment comment = commentRepository
                            .findById(id)
                            .orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
    }


}
