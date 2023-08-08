package Project.Graduation.controller;

import Project.Graduation.model.Comment;
import Project.Graduation.model.Resume;
import Project.Graduation.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }
    @PostMapping("/save")
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.resumeService.createResume(resume));
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Resume> getById(@PathVariable Long id) throws FileNotFoundException {
        Resume resume=this.resumeService.getResumeById(id);
        return ResponseEntity.ok(resume);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Resume> updateResume(@RequestBody Resume resume , @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.resumeService.updateResume(id,resume));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Resume> deleteResume(@PathVariable Long id) throws FileNotFoundException {
        Resume deleted=this.resumeService.deleteResume(id);
        return ResponseEntity.ok(deleted);
    }
}
