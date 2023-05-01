package Project.Graduation.service;

import Project.Graduation.model.TopicFiles;
import Project.Graduation.model.Topics;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TopicFileService {
    List<TopicFiles> getAllTopicFiles();
    TopicFiles getTopicFileById(Long id);
    TopicFiles createTopicFile(TopicFiles topic);
    TopicFiles updateTopicFile(Long id, TopicFiles topic);
    TopicFiles deleteTopicFile(Long id);
    String uploadFile(Long id, MultipartFile file) throws IOException;
}
