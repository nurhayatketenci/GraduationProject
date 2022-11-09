package Project.Graduation.service.implementations;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Project.Graduation.model.Instructor;
import Project.Graduation.repository.InstructorRepository;
import Project.Graduation.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService{
    private InstructorRepository instructorRepository;
	@Override
	public List<Instructor> getAllInstructors() {
		List<Instructor> instructors=this.instructorRepository.findAll();
		return instructors;
	}

	@Override
	public ResponseEntity<Instructor> getInstructor(int id) {
		 Instructor instructor=this.instructorRepository.findById(id).orElseThrow(NoSuchElementException ::new);
		 return new ResponseEntity<>(instructor,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Instructor> addInstructor(Instructor instructor) {
		Instructor newInstructor=this.instructorRepository.save(instructor);
		return new ResponseEntity<>(newInstructor, HttpStatus.CREATED); 
	}

	@Override
	public ResponseEntity<Instructor> updateInstructor(int id, Instructor newInstructor) {
		Instructor instructor=this.instructorRepository.findById(id).orElseThrow(NoSuchElementException :: new);
		instructor.setCountry(newInstructor.getCountry());
		instructor.setFirstName(newInstructor.getFirstName());
		instructor.setLastName(newInstructor.getLastName());
		Instructor updatedInstructor=instructorRepository.save(instructor);
		return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Instructor> deleteInstructor(int id) {
		this.instructorRepository.deleteById(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
