package Project.Graduation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import Project.Graduation.model.Lesson;

public interface LessonService {

	List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
    Lesson addLesson(Lesson lesson);
    Lesson updateLesson(Long id, Lesson newLesson);
	Lesson deleteLesson(Long id) ;
}
