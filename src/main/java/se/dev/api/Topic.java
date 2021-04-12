package se.dev.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private List<Article> relatedArticle = new ArrayList<>() ;

    public List<Article> getRelatedArticle() {
        return relatedArticle;
    }

    public void setRelatedArticle(List<Article> relatedArticle) {
        this.relatedArticle = relatedArticle;
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
}
