package Project.Graduation.controller;

import Project.Graduation.model.Instructor;
import Project.Graduation.model.Language;
import Project.Graduation.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService){this.languageService=languageService;}

    @GetMapping("/getall")
    ResponseEntity<List<Language>> getAll(){
        return ResponseEntity.ok(languageService.getAllLanguage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(languageService.getLanguageById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Language> deleteLanguage(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(languageService.deleteLanguage(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable(value = "id") Long id,  @RequestBody Language newLanguage) {
        return ResponseEntity.ok(languageService.updateLanguage(id, newLanguage));
    }
    @PostMapping("/create")
    public ResponseEntity<Language> createLanguage(@RequestBody Language language){
        return ResponseEntity.ok(languageService.addLanguage(language));
    }

}
