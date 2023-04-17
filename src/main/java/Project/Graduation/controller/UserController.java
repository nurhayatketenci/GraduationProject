package Project.Graduation.controller;

import Project.Graduation.repository.UserRepository;
import nonapi.io.github.classgraph.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import Project.Graduation.model.User;
import Project.Graduation.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${app.upload.dir}")
    private String uploadDir;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/getall")
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/getbyid/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
    
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}
    @PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,  @RequestBody User newUser) {
    	return ResponseEntity.ok(userService.updateUser(id, newUser));
	}
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
    	return ResponseEntity.ok(userService.addUser(user));
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long userId,
                                         @RequestParam("image") MultipartFile image) throws IOException {
        String imagePath = userService.uploadImage(userId, image);
        return ResponseEntity.ok(imagePath);
    }
    @GetMapping("/{id}/image/{imageName}")
    public ResponseEntity<ByteArrayResource> getUserImage(@PathVariable String id,
                                                          @PathVariable String imageName) throws IOException {
        String imagePath = uploadDir + imageName;

        // Dosya yolunu belirleyin
        Path imagePathObj = Paths.get(imagePath);

        // Resmi okuyun
        byte[] imageData = Files.readAllBytes(imagePathObj);

        // MIME türünü belirleyin
        String contentType = Files.probeContentType(imagePathObj);

        // Byte verilerini içeren bir ByteArrayResource nesnesi oluşturun
        ByteArrayResource resource = new ByteArrayResource(imageData);

        // ResponseEntity'yi döndürün
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/info")
    public User getUserDetails(){
        return this.userService.getUserDetails();
    }


   
   
  

    
   

}
