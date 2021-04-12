package se.dev.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c.authorName FROM Comment c where c.authorName = :commentAuthorName")
    List<Comment> findByAuthorName(@Param("commentAuthorName") String authorName);

}
