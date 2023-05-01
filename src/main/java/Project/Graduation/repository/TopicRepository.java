package Project.Graduation.repository;

import Project.Graduation.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topics, Long> {
    boolean existsByHeader(String header);
}
