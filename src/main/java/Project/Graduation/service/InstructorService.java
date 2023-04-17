package Project.Graduation.service;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import Project.Graduation.model.Instructor;
import org.springframework.web.multipart.MultipartFile;

public interface InstructorService {
	List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor addInstructor(Instructor instructor);
    Instructor updateInstructor(Long id, Instructor newInstructor);
	Instructor deleteInstructor(Long id) ;
}
