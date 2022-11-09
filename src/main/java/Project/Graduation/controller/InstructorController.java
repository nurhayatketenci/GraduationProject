package Project.Graduation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.Graduation.model.Instructor;
import Project.Graduation.service.InstructorService;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
      private InstructorService instructorService;
      
      @Autowired
      public InstructorController(InstructorService instructorService) {
    	  this.instructorService=instructorService;
      }
      
        @GetMapping("/getall")
        ResponseEntity<?> getAll(){
            return ResponseEntity.ok(instructorService.getAllInstructors());
        }
        
	    @GetMapping("/{id}")
	  	public ResponseEntity<Instructor> getInstructor(@PathVariable(name = "id") int id) {
	  		return instructorService.getInstructor(id);
	  	}
	      
	    @DeleteMapping("/delete/{id}")
	  	public ResponseEntity<Instructor> deleteInstructor(@PathVariable(name = "id") int id) {
	  		return instructorService.deleteInstructor(id);
	  	}
	    @PutMapping("/update/{id}")
	  	public ResponseEntity<Instructor> updateInstructor(@PathVariable(value = "id") int id,  @RequestBody Instructor newInstructor) {
	      	return instructorService.updateInstructor(id, newInstructor);
	  	}
	    @PostMapping("/create")
	    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor){
	    	return instructorService.addInstructor(instructor);
	    }
	    
      
}
