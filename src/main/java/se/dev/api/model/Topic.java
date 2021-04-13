/**
 * Represents the Topic
 */
package se.dev.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinColumn(nullable = false)
    private List<Article> relatedArticle= new ArrayList<>();

    public Topic(){}
    public Topic(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getRelatedArticle() {
        return relatedArticle;
    }

    public void setRelatedArticle(List<Article> relatedArticle) {
        this.relatedArticle = relatedArticle;
    }
}
