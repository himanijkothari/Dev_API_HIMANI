/**
 * Jpa repository for topic entity
 */
package se.dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dev.api.model.Topic;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
