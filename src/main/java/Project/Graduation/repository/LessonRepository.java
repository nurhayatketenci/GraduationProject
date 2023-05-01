package Project.Graduation.repository;

import Project.Graduation.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import Project.Graduation.model.Lesson;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
