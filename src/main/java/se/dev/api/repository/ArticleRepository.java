/**
 * Jpa repository for article entity
 */

package se.dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dev.api.model.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
