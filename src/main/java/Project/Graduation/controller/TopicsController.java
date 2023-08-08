package Project.Graduation.controller;

import Project.Graduation.model.Topics;
import Project.Graduation.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicsController {
    private TopicService topicService;
    @Autowired
    public TopicsController(TopicService topicService){this.topicService=topicService;}

    @GetMapping("/getall")
    ResponseEntity<List<Topics>> getAll(){
       return ResponseEntity.ok(this.topicService.getAllTopics());
    }
    @GetMapping("/{id}")
     ResponseEntity<Topics> getTopicById(@PathVariable(name="id") Long id){
    return ResponseEntity.ok(topicService.getTopicById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic){
        return ResponseEntity.ok(topicService.createTopic(topic));
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Topics> deleteTopic(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(topicService.deleteTopic(id));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<Topics> updateTopic(@PathVariable(value="id") Long id,@RequestBody Topics newTopic){
        return ResponseEntity.ok(topicService.updateTopic(id, newTopic));
    }
    @PostMapping("/uploadimage/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long userId,
                                         @RequestParam("image") MultipartFile image) throws IOException {
        String imagePath = topicService.uploadImage(userId, image);
        return ResponseEntity.ok(imagePath);
    }

    //ClassPathResource sınıfını kullanarak dosyayı okuyoruz
    @GetMapping("/image/{id}")
    public ResponseEntity<ByteArrayResource>  getPhotoById(@PathVariable Long id) throws IOException {
        Topics topic = topicService.getTopicById(id);
        String imagePath = topic.getImagePath();

        Path imagePathObj = Paths.get(imagePath);
        byte[] imageData = Files.readAllBytes(imagePathObj);
        String contentType = Files.probeContentType(imagePathObj);
        ByteArrayResource resource = new ByteArrayResource(imageData);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
