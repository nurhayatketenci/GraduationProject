package Project.Graduation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Project.Graduation.model.Instructor;
import Project.Graduation.service.InstructorService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
      private InstructorService instructorService;
      
      @Autowired
      public InstructorController(InstructorService instructorService) {
    	  this.instructorService=instructorService;
      }
      
        @GetMapping("/getall")
		public ResponseEntity<List<Instructor>> getAll(){
            return ResponseEntity.ok(instructorService.getAllInstructors());
        }
        
	    @GetMapping("/getbyid/{id}")
	  	public ResponseEntity<Instructor> getInstructorById(@PathVariable(name = "id") Long id) {
	  		return ResponseEntity.ok(instructorService.getInstructorById(id));
	  	}
	      
	    @DeleteMapping("/delete/{id}")
	  	public ResponseEntity<Instructor> deleteInstructor(@PathVariable(name = "id") Long id) {
	  		return ResponseEntity.ok(instructorService.deleteInstructor(id));
	  	}
	    @PutMapping("/update/{id}")
	  	public ResponseEntity<Instructor> updateInstructor(@PathVariable(value = "id") Long id,  @RequestBody Instructor newInstructor) {
	      	return ResponseEntity.ok(instructorService.updateInstructor(id, newInstructor));
	  	}
	    @PostMapping("/create")
	    public ResponseEntity<Instructor> createInstructor( @RequestBody Instructor instructor) throws IOException {
	    	return ResponseEntity.ok(instructorService.addInstructor(instructor));
	    }
	    
      
}
