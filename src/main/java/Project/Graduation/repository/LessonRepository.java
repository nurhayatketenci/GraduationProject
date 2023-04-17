package Project.Graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Project.Graduation.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
