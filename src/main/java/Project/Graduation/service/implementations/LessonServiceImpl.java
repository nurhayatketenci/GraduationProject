package Project.Graduation.service.implementations;

import java.util.List;
import java.util.NoSuchElementException;

import Project.Graduation.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Project.Graduation.model.Lesson;
import Project.Graduation.repository.LessonRepository;
import Project.Graduation.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{
	@Autowired
	private LessonRepository lessonRepository;

	@Override
	public List<Lesson> getAllLessons() {
		List<Lesson> lessons=this.lessonRepository.findAll();
		return lessons;
	}

	@Override
	public Lesson getLessonById(Long id) {
		return lessonRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Couldnt find lesson by id: " + id));
	}

	@Override
	public Lesson addLesson(Lesson lesson) {
		return lessonRepository.save(lesson);
	}

	@Override
	public Lesson updateLesson(Long id, Lesson newLesson) {
		Lesson oldLesson=getLessonById(id);
		//oldLesson.setInstructor(newLesson.getInstructor());
		oldLesson.setDate(newLesson.getDate());
		oldLesson.setLink(newLesson.getLink());
		oldLesson.setLanguage(newLesson.getLanguage());
		return lessonRepository.save(oldLesson);
	}

	@Override
	public Lesson deleteLesson(Long id) {
		Lesson lesson=getLessonById(id);
		lessonRepository.delete(lesson);
		return lesson;
	}

}
