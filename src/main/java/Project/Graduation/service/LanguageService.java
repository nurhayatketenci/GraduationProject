package Project.Graduation.service;

import Project.Graduation.model.Instructor;
import Project.Graduation.model.Language;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageService {
    List<Language> getAllLanguage();
    Language getLanguageById(Long id);
    Language addLanguage(Language language);
    Language updateLanguage(Long id, Language newLanguage);
    Language deleteLanguage(Long id) ;
}
