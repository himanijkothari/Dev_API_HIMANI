/**
 * Represents the controller class for Topic entity
 * that manages the request and response
 */
package se.dev.api.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dev.api.api.exception.ResourceNotFoundException;
import se.dev.api.model.Article;
import se.dev.api.model.Topic;
import se.dev.api.repository.ArticleRepository;
import se.dev.api.repository.TopicRepository;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    TopicRepository topicRepository;
    ArticleRepository articleRepository;

    public TopicController(TopicRepository topicRepository,ArticleRepository articleRepository)
    {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/topics")
    public List<Topic> listAllTopics()
    {
        List<Topic> topics = topicRepository.findAll();
        return topics;
    }

    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<List<Topic>> listAllTopicOnArticle(@PathVariable Long articleId)
    {
        Article article = articleRepository
                            .findById(articleId)
                            .orElseThrow(ResourceNotFoundException::new);
        List<Topic> topics = article.getRelatedTopics();
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/articles/{articleId}/topics") //problem
    public ResponseEntity<Topic> createTopicToArticle(@PathVariable Long articleId, @Valid @RequestBody Topic topic)
    {
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        Optional<Topic> topic1 = topicRepository.findById(topic.getId());

        if (topic1.isEmpty()) {
            topicRepository.save(topic);
        }
        topic.getRelatedArticle().add(article);
        topicRepository.save(topic);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(topic);

    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic)
    {
        topicRepository.save(topic);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(topic);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> updateTopic(@RequestBody Topic updatedTopic, @PathVariable Long id)
    {
        topicRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedTopic.setId(id);
        Topic topic = topicRepository.save(updatedTopic);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/topics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable Long id)
    {
        Topic topic = topicRepository
                        .findById(id)
                        .orElseThrow(ResourceNotFoundException::new);
        topicRepository.delete(topic);
    }

    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheAssociation(@PathVariable Long articleId,@PathVariable Long topicId)
    {
        Article article = articleRepository
                            .findById(articleId)
                            .orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository
                            .findById(topicId)
                            .orElseThrow(ResourceNotFoundException::new);

        if(topic.getRelatedArticle().contains(article)) {
            topic.getRelatedArticle().remove(article);
            topicRepository.save(topic);
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<List<Article>> listAllArticleByTopic(@PathVariable Long topicId)
    {
        Topic topic = topicRepository
                        .findById(topicId)
                        .orElseThrow(ResourceNotFoundException::new);
        List<Article> articles = topic.getRelatedArticle();
        return ResponseEntity.ok(articles);
    }


}
