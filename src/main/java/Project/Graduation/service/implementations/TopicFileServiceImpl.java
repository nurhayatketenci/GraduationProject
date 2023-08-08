package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.model.TopicFiles;
import Project.Graduation.repository.TopicFileRepository;
import Project.Graduation.service.TopicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TopicFileServiceImpl implements TopicFileService {
    @Value("${app.upload.dir}")
    private String uploadDir;
    private final Path root = Paths.get("uploads");
    @Autowired
    private TopicFileRepository topicFileRepository;
    @Autowired
    ResourceLoader resourceLoader;

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
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public List<TopicFiles> findByTopicsId(Long topicsId) {
        List<TopicFiles> newList=this.topicFileRepository.findByTopicsId(topicsId);

        return newList;
    }


    @Override
    public String uploadFile(Long id, MultipartFile file) throws Exception {
        TopicFiles topicFile = topicFileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with id: " + id));

        try {
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = topicFile.getHeader() + fileExtension;
            Path newFilePath = this.root.resolve(newFilename);

            if (Files.exists(newFilePath)) {
                throw new FileAlreadyExistsException("A file with the same name already exists.");
            }

            Files.copy(file.getInputStream(), newFilePath);

            // Veritabanında dosya yolunu güncelle
            topicFile.setFilePath(newFilename);
            topicFileRepository.save(topicFile);

            return "File uploaded successfully.";
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file with the same name already exists.");
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }

    }


}
