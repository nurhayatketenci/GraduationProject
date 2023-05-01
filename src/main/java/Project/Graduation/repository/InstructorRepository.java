package Project.Graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Project.Graduation.model.Instructor;
import java.util.Optional;


public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByEmail(String email);
}
