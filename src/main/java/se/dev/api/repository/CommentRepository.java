/**
 * Jpa repository for comment entity
 */

package se.dev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.dev.api.model.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //Return the comment whose authorName matches the parameter of the method
    @Query("SELECT c FROM Comment c where c.authorName = :commentAuthorName")
    Optional<List<Comment>> findByAuthorName(@Param("commentAuthorName") String authorName);


}
