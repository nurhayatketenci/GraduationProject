package Project.Graduation.repository;

import Project.Graduation.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Instructor, Integer> {
}
