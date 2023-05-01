package Project.Graduation.repository;

import Project.Graduation.model.TopicFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicFileRepository extends JpaRepository<TopicFiles, Long>{
    boolean existsByHeader(String header);
}
