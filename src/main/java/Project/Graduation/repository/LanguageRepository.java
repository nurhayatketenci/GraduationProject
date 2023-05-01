package Project.Graduation.repository;

import Project.Graduation.model.Language;
import Project.Graduation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLanguageName(String languageName);
}
