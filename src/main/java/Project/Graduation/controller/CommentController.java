package Project.Graduation.controller;

import Project.Graduation.model.Comment;
import Project.Graduation.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<List<Comment>> getCommentsByInstructorId(@PathVariable Long id) {
        List<Comment> comments = commentService.getAllCommentByInstructorId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/save")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Comment deletedComment = commentService.delete(id);
        return ResponseEntity.ok(deletedComment);
    }

    @GetMapping("/getcommentbylearnerandinstructorid/{learner_id}/{instructor_id}")
    public ResponseEntity<Comment> getCommentByLearnerIdAndInstructorId(@PathVariable Long learner_id , @PathVariable Long instructor_id){
        Comment comment=this.commentService.findByLearner_IdAndInstructor_Id(learner_id,instructor_id);
        return ResponseEntity.ok(comment);
    }
}
