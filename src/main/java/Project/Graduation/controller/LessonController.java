package Project.Graduation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Project.Graduation.model.Lesson;
import Project.Graduation.service.LessonService;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
   private LessonService lessonService;
   
   @Autowired
   public LessonController(LessonService lessonService) {
       this.lessonService = lessonService;
   }
   @GetMapping("getall")
   ResponseEntity<?> getAll(){
       return ResponseEntity.ok(lessonService.getAllLessons());
   }
   @GetMapping("/{id}")
	public ResponseEntity<Lesson> getLesson(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(lessonService.getLessonById(id));
	}
   
   @DeleteMapping("/delete/{id}")
	public ResponseEntity<Lesson> deleteLesson(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(lessonService.deleteLesson(id));
	}
   @PutMapping("/update/{id}")
	public ResponseEntity<Lesson> updateLesson(@PathVariable(value = "id") Long id,  @RequestBody Lesson newLesson) {
   	return ResponseEntity.ok(lessonService.updateLesson(id, newLesson));
	}
   @PostMapping("create")
   public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson){
   	return ResponseEntity.ok(lessonService.addLesson(lesson));
   }
}
