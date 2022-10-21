package Project.Graduation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Project.Graduation.model.User;

public interface UserService {
    List<User> getAllUsers();
    ResponseEntity<User> getUser(int id);
    ResponseEntity<User> addUser(User user);
    ResponseEntity<User> updateUser(int id, User newUser);
	ResponseEntity<User> deleteUser(int id) ;
     
}
