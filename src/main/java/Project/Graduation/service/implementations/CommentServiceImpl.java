package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.model.Comment;
import Project.Graduation.model.Instructor;
import Project.Graduation.repository.CommentRepository;
import Project.Graduation.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments=this.commentRepository.findAll();
        return comments;
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NoDataFoundException("Couldnt find lesson by id: " + id));

    }

    @Override
    public List<Comment> getAllCommentByInstructorId(Long id) {
        List<Comment> commentByInstructor=this.commentRepository.findByInstructor_Id(id);
        return commentByInstructor;
    }

    @Override
    public Comment delete(Long id) {
        Comment comment=getById(id);
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment save(Comment comment) {
       return commentRepository.save(comment);
    }

    @Override
    public Comment findByLearner_IdAndInstructor_Id(Long learner_id ,Long instructor_id){
        Comment comment=this.commentRepository.findByLearner_IdAndInstructor_Id(learner_id,instructor_id);
        return comment;
    }
}
