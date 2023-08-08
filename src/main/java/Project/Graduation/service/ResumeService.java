package Project.Graduation.service;

import Project.Graduation.model.Resume;

import java.io.FileNotFoundException;
import java.util.List;

public interface ResumeService {
    Resume getResumeById(Long id) throws FileNotFoundException;
    Resume createResume(Resume resume);
    Resume updateResume(Long id, Resume resume);
    Resume deleteResume(Long id) throws FileNotFoundException;
}