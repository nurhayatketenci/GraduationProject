package Project.Graduation.repository;

import Project.Graduation.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume , Long> {
}
