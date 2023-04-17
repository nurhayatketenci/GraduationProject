package Project.Graduation.service;

import Project.Graduation.model.User;
import Project.Graduation.model.UserImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserImageService {
    public String uploadImage(Long userId, MultipartFile file) throws IOException;
    UserImage updateUserImage(Long id, UserImage newUserImage);
    UserImage deleteUserImage(Long id) ;
    List<UserImage> getAllUserImage();
    User getUserImageById(Long id);
}
