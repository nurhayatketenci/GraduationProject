package Project.Graduation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import Project.Graduation.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    User updateUser(Long id, User newUser);
	User deleteUser(Long id) ;
    public String uploadImage(Long userId, MultipartFile file) throws IOException;
    public User getUserDetails();

}
