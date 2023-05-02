package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.model.Topics;
import Project.Graduation.model.User;
import Project.Graduation.repository.TopicRepository;
import Project.Graduation.service.TopicService;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Value("${app.upload.dir-topic}")
    private String uploadDir;

    @Override
    public List<Topics> getAllTopics() {
        List<Topics> topics=this.topicRepository.findAll();
        return topics;
    }

    @Override
    public Topics getTopicById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Couldnt find topic by id: " + id));
    }

    @Override
    public Topics createTopic(Topics topic) {
        boolean isTopicExists=topicRepository.existsByHeader(topic.getHeader());
        if(isTopicExists){
            throw new RecordAlreadyExistsException("This topic is already exists.");
        }
        return topicRepository.save(topic);
    }

    @Override
    public Topics updateTopic(Long id, Topics newTopic) {
        Topics oldTopic=getTopicById(id);
        oldTopic.setHeader(newTopic.getHeader());
        oldTopic.setDescription(newTopic.getDescription());

        return topicRepository.save(oldTopic);
    }

    @Override
    public Topics deleteTopic(Long id) {
        Topics topic=getTopicById(id);
        topicRepository.delete(topic);
        return topic;
    }

    @Override
    public String uploadImage(Long id, MultipartFile file) throws IOException {
        Topics topic=getTopicById(id);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        String newFileName = "topic_" + topic.getId() + "." + fileExtension;
        String uploadPath = uploadDir + "/" + newFileName;
        Path uploadPathDir = Paths.get(uploadDir);
        if (!Files.exists(uploadPathDir)) {
            Files.createDirectories(uploadPathDir);
        }

        Path uploadPathFile = Paths.get(uploadPath);
        Files.deleteIfExists(uploadPathFile);
        Files.copy(file.getInputStream(), uploadPathFile);

        topic.setImagePath(uploadPath);
        topicRepository.save(topic);

        return uploadPath;
    }

}
