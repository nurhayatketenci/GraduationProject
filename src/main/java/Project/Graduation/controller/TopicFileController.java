package Project.Graduation.controller;

import Project.Graduation.model.TopicFiles;
import Project.Graduation.service.TopicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/topicfiles")
public class TopicFileController {

    @Autowired
    private TopicFileService topicFileService;

    @GetMapping("/getall")
    public ResponseEntity<List<TopicFiles>> getAllTopicFiles() {
        List<TopicFiles> topicFiles = topicFileService.getAllTopicFiles();
        return ResponseEntity.ok(topicFiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicFiles> getTopicFileById(@PathVariable Long id) {
        TopicFiles topicFile = topicFileService.getTopicFileById(id);
        return ResponseEntity.ok(topicFile);
    }

    @PostMapping("/create")
    public ResponseEntity<TopicFiles> createTopicFile(@RequestBody TopicFiles topicFile) {
        TopicFiles createdTopicFile = topicFileService.createTopicFile(topicFile);
        if (createdTopicFile == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdTopicFile);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicFiles> updateTopicFile(@PathVariable Long id, @RequestBody TopicFiles topicFile) {
        TopicFiles updatedTopicFile = topicFileService.updateTopicFile(id, topicFile);
        return ResponseEntity.ok(updatedTopicFile);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TopicFiles> deleteTopicFile(@PathVariable Long id) {
        TopicFiles deletedTopicFile = topicFileService.deleteTopicFile(id);
        return ResponseEntity.ok(deletedTopicFile);
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id,
                                         @RequestParam("file") MultipartFile file) throws IOException {
        String imagePath = topicFileService.uploadFile(id,file);
        return ResponseEntity.ok(imagePath);
    }

}
