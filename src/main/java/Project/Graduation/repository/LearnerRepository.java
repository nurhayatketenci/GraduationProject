package Project.Graduation.repository;

import Project.Graduation.model.Instructor;
import Project.Graduation.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LearnerRepository extends JpaRepository<Learner,Long> {
    Optional<Learner> findByEmail(String email);

}
