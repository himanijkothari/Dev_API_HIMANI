/**
 * Represents the article
 */
package se.dev.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;


@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "article")
    @JsonIgnore
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "relatedArticle")
    private List<Topic> relatedTopics;

    public Article() { }

    public Article(String title, String body, String authorName) {
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Topic> getRelatedTopics() {
        return relatedTopics;
    }

    public void setRelatedTopics(List<Topic> relatedTopics) {
        this.relatedTopics = relatedTopics;
    }
}
