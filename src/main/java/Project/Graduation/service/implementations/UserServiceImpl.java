package Project.Graduation.service.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.model.Instructor;
import Project.Graduation.model.UserImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import Project.Graduation.model.User;
import Project.Graduation.repository.UserRepository;
import Project.Graduation.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	 private UserRepository userRepository;

	@Value("${app.upload.dir}")
	private String uploadDir;


	@Override
	public List<User> getAllUsers() {
		return new ArrayList<User>((Collection<? extends User>) userRepository.findAll());
	}
	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Couldnt find user by id: " + id));
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User newUser) {
		User user=this.userRepository.findById(id).orElseThrow(NoSuchElementException :: new);
		user.setUsername(newUser.getUsername());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setAge(newUser.getAge());
		user.setEmail(newUser.getEmail());
		user.setPhoneNumber(newUser.getPhoneNumber());
		return userRepository.save(user);
	}
	@Override
	public String uploadImage(Long userId, MultipartFile file) throws IOException {
		User user = userRepository.findById(userId)
				.orElseThrow(() ->  new RuntimeException("Couldnt find user by id: " + userId));

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileExtension = StringUtils.getFilenameExtension(fileName);
		String newFileName = "user_" + user.getId() + "." + fileExtension;
		String uploadPath = uploadDir + "/" + newFileName;
		Path uploadPathDir = Paths.get(uploadDir);
		if (!Files.exists(uploadPathDir)) {
			Files.createDirectories(uploadPathDir);
		}

		Path uploadPathFile = Paths.get(uploadPath);
		Files.deleteIfExists(uploadPathFile);
		Files.copy(file.getInputStream(), uploadPathFile);

		user.setImagePath(uploadPath);
		userRepository.save(user);

		return uploadPath;
	}


	@Override
	public User deleteUser(Long id) {
		User user=getUserById(id);
		userRepository.save(user);
		return user;
	}
	@Override
	public User getUserDetails(){
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findByEmail(email).get();
	}




}
