package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.model.Resume;
import Project.Graduation.repository.ResumeRepository;
import Project.Graduation.service.ResumeService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ResumeServiceImpl implements ResumeService {
    private ResumeRepository resumeRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public Resume getResumeById(Long id) throws FileNotFoundException {
        Resume resume=this.resumeRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Resume not found with id: " + id));
        return resume;
    }

    @Override
    public Resume createResume(Resume resume) {
        return this.resumeRepository.save(resume);
    }

    @Override
    public Resume updateResume(Long id, Resume resume) {
        Resume existingResume = resumeRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Resume not found"));
        existingResume.setExperience(resume.getExperience());
        existingResume.setProjects(resume.getProjects());
        existingResume.setCoverLetter(resume.getCoverLetter());
        return resumeRepository.save(existingResume);
    }


    @Override
    public Resume deleteResume(Long id) throws FileNotFoundException {
        Resume resume=getResumeById(id);
        this.resumeRepository.delete(resume);
        return resume;

    }
}
