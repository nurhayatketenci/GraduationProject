package Project.Graduation.repository;

import Project.Graduation.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByInstructor_Id(Long id);
    Comment findByLearner_IdAndInstructor_Id(Long learner_id ,Long instructor_id);
}
