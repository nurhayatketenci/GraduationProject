package Project.Graduation.controller;

import Project.Graduation.model.Learner;
import Project.Graduation.service.LearnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learners")
public class LearnerController {
    private LearnerService learnerService;

    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping
    public ResponseEntity<List<Learner>> getAllLearners() {
        List<Learner> learners = learnerService.getAllLearner();
        return new ResponseEntity<>(learners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Learner> getLearnerById(@PathVariable Long id) {
        Learner learner = learnerService.getLearnerById(id);
        return new ResponseEntity<>(learner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Learner> addLearner(@RequestBody Learner learner) {
        Learner addedLearner = learnerService.addLearner(learner);
        return new ResponseEntity<>(addedLearner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Learner> updateLearner(@PathVariable Long id, @RequestBody Learner learner) {
        Learner updatedLearner = learnerService.updateLearner(id, learner);
        return new ResponseEntity<>(updatedLearner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Learner> deleteLearner(@PathVariable Long id) {
        Learner deletedLearner = learnerService.deleteLearner(id);
        return new ResponseEntity<>(deletedLearner, HttpStatus.OK);
    }
}
