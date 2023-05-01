package Project.Graduation.service.implementations;

import java.io.File;
import java.io.IOException;
import java.util.*;

import Project.Graduation.exception.RecordAlreadyExistsException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Project.Graduation.model.Instructor;
import Project.Graduation.repository.InstructorRepository;
import Project.Graduation.service.InstructorService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InstructorServiceImpl implements InstructorService{
	private static Logger logger = LogManager.getLogger(InstructorServiceImpl.class);
	@Autowired
    private InstructorRepository instructorRepository;
	@Override
	public List<Instructor> getAllInstructors() {
		return  new ArrayList<Instructor>((Collection<? extends Instructor>) instructorRepository.findAll());
	}

	@Override
	public Instructor getInstructorById(Long id) {
		 return instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldnt find instructor by id: " + id));
	}

	@Override
	public Instructor addInstructor(Instructor instructor) {
        Optional<Instructor> checkInstructor=instructorRepository.findByEmail(instructor.getEmail());
		if(!checkInstructor.isPresent()){
			return instructorRepository.save(instructor);
		}
		throw new RecordAlreadyExistsException("This email is already exists.");

	}

	@Override
	public Instructor updateInstructor(Long id, Instructor newInstructor) {
		Instructor oldInstructor=getInstructorById(id);
		oldInstructor.setDescription(newInstructor.getDescription());
		return instructorRepository.save(oldInstructor);
	}

	@Override
	public Instructor deleteInstructor(Long id) {
		Instructor instructor=getInstructorById(id);
	    instructorRepository.delete(instructor);
		return  instructor;
	}



}
