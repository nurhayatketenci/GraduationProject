package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.model.TopicFiles;
import Project.Graduation.model.Topics;
import Project.Graduation.repository.TopicFileRepository;
import Project.Graduation.repository.TopicRepository;
import Project.Graduation.service.TopicFileService;
import Project.Graduation.service.TopicService;
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
public class TopicFileServiceImpl implements TopicFileService {
    @Value("${app.upload.dir-file}")
    private String uploadDir;
    @Autowired
    private TopicFileRepository topicFileRepository;


    @Override
    public List<TopicFiles> getAllTopicFiles() {
        List<TopicFiles> topicFiles=this.topicFileRepository.findAll();
        return topicFiles;
    }

    @Override
    public TopicFiles getTopicFileById(Long id) {
        return topicFileRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Couldnt find topic file by id: " + id));

    }

    @Override
    public TopicFiles createTopicFile(TopicFiles topicFile) {
        boolean isTopicExists=topicFileRepository.existsByHeader(topicFile.getHeader());
        if(isTopicExists){
            throw new RecordAlreadyExistsException("This topic alreaady exists.");
        }
        return topicFileRepository.save(topicFile);
    }

    @Override
    public TopicFiles updateTopicFile(Long id, TopicFiles newTopic) {
        TopicFiles oldTopic=getTopicFileById(id);
        oldTopic.setHeader(newTopic.getHeader());
        oldTopic.setDescription(newTopic.getDescription());

        return topicFileRepository.save(oldTopic);
    }

    @Override
    public TopicFiles deleteTopicFile(Long id) {
        TopicFiles topicFiles=getTopicFileById(id);
        topicFileRepository.delete(topicFiles);
        return topicFiles;
    }

    @Override
    public String uploadFile(Long id, MultipartFile file) throws IOException {
        TopicFiles topicFiles=getTopicFileById(id);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension=StringUtils.getFilenameExtension(fileName);
        String newFileName = "topic_" + topicFiles.getId() + "." + fileExtension;
        String uploadPath = uploadDir + "/" + newFileName;
        Path uploadPathDir = Paths.get(uploadDir);
        if (!Files.exists(uploadPathDir)) {
            Files.createDirectories(uploadPathDir);
        }

        Path uploadPathFile = Paths.get(uploadPath);
        Files.deleteIfExists(uploadPathFile);
        Files.copy(file.getInputStream(), uploadPathFile);
        topicFiles.setFilePath(uploadPath);
        topicFileRepository.save(topicFiles);
        return uploadPath;
    }
}
