package Project.Graduation.service;

import Project.Graduation.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    Comment getById(Long id);
    List<Comment> getAllCommentByInstructorId(Long id);
    Comment delete(Long id);
    Comment save(Comment comment);
    Comment findByLearner_IdAndInstructor_Id(Long learner_id ,Long instructor_id);

}
