package Project.Graduation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import Project.Graduation.model.User;
import Project.Graduation.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("getall")
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") int id) {
		return userService.getUser(id);
	}
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(name = "id") int id) {
		return userService.deleteUser(id);
	}
    
   
   
  

    
   

}
