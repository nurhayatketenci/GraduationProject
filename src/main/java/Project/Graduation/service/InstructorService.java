package Project.Graduation.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import Project.Graduation.model.Instructor;
public interface InstructorService {
	List<Instructor> getAllInstructors();
    ResponseEntity<Instructor> getInstructor(int id);
    ResponseEntity<Instructor> addInstructor(Instructor instructor);
    ResponseEntity<Instructor> updateInstructor(int id, Instructor newInstructor);
	ResponseEntity<Instructor> deleteInstructor(int id) ;
}
