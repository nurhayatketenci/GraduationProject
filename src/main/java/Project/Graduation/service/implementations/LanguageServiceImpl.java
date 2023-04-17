package Project.Graduation.service.implementations;

import Project.Graduation.model.Instructor;
import Project.Graduation.model.Language;
import Project.Graduation.repository.LanguageRepository;
import Project.Graduation.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LanguageServiceImpl implements LanguageService {
   @Autowired
   private LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguage() {
        return  new ArrayList<Language>((Collection<? extends Language>) languageRepository.findAll());
    }

    @Override
    public Language getLanguageById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldnt find language by id: " + id));
    }

    @Override
    public Language addLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language updateLanguage(Long id, Language newLanguage) {
        Language language = getLanguageById(id);
        language.setLanguageName(newLanguage.getLanguageName());
        return languageRepository.save(language);
    }

    @Override
    public Language deleteLanguage(Long id) {
        Language language=getLanguageById(id);
        languageRepository.delete(language);
        return language;
    }
}
