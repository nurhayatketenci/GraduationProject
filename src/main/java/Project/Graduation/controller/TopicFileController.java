package Project.Graduation.controller;

import Project.Graduation.model.TopicFiles;
import Project.Graduation.service.TopicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/topicfiles")
public class TopicFileController {

    @Autowired
    private TopicFileService topicFileService;
    private final Path root=Paths.get("C:\\Users\\Nurhayat\\Desktop\\Graduation\\uploads\\");;

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
    @PostMapping("/{id}/file")
    public ResponseEntity<?> uploadFile(@PathVariable("id") Long id,
                                         @RequestParam("file") MultipartFile file) throws Exception {
        String imagePath = topicFileService.uploadFile(id,file);
        return ResponseEntity.ok(imagePath);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable Long id) throws IOException {
        TopicFiles topicFile = topicFileService.getTopicFileById(id);
        String filePath = topicFile.getFilePath();
        Path file = this.root.resolve(filePath);
        if (!Files.exists(file)) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        byte[] fileContent = Files.readAllBytes(file);
        String filename = topicFile.getHeader() + ".pdf";
        ByteArrayResource resource = new ByteArrayResource(fileContent);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + filename)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(fileContent.length)
                .body(resource);
    }
    @GetMapping("/view/getbytopicid/{id}")  
    public ResponseEntity<List<TopicFiles>> viewFileByTopicId(@PathVariable Long id) throws IOException{
        List<TopicFiles> topicFiles = topicFileService.findByTopicsId(id);
        return ResponseEntity.ok(topicFiles);

    }




}
