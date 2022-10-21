package Project.Graduation.service.implementations;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Project.Graduation.model.User;
import Project.Graduation.repository.UserRepository;
import Project.Graduation.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	   private UserRepository userRepository;
	   
	   @Autowired
	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	@Override
	public List<User> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		return users;
	}
	
	@Override
	public ResponseEntity<User> getUser(int id) {
        User user=this.userRepository.findById(id).orElseThrow(NoSuchElementException ::new);
        //orElseThrow->varsa al yoksa hata fırlat 
        //ResourceNotFoundException->kaynak bulunamadi hatasi 
        // optional boş ise NoSuchElementException
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> addUser(User user) {
		User newUser=this.userRepository.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED); 
	}

	@Override
	public ResponseEntity<User> updateUser(int id, User newUser) {
		User user=this.userRepository.findById(id).orElseThrow(NoSuchElementException :: new);
		user.setUsername(newUser.getUsername());
		User updatedUser=this.userRepository.save(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> deleteUser(int id) {
		this.userRepository.deleteById(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	


	

	

	

		

	
		   
	

}
