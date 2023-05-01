package Project.Graduation.service;

import Project.Graduation.model.Topics;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TopicService {
   List<Topics> getAllTopics();
   Topics getTopicById(Long id);
   Topics createTopic(Topics topic);
   Topics updateTopic(Long id, Topics topic);
   Topics deleteTopic(Long id);
   String uploadImage(Long id, MultipartFile file) throws IOException;
}
