package Project.Graduation.service.implementations;

import Project.Graduation.exception.NoDataFoundException;
import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.model.Learner;
import Project.Graduation.repository.LearnerRepository;
import Project.Graduation.service.LearnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerServiceImpl implements LearnerService {
    private LearnerRepository learnerRepository;

    public LearnerServiceImpl(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    @Override
    public List<Learner> getAllLearner() {
     List<Learner> learners=this.learnerRepository.findAll();
     return learners;
    }

    @Override
    public Learner getLearnerById(Long id) {
        return this.learnerRepository.findById(id).orElseThrow(() -> new NoDataFoundException("Couldnt find Learner by id: " + id));
    }

    @Override
    public Learner addLearner(Learner learner) {
        Optional<Learner> checkInstructor=learnerRepository.findByEmail(learner.getEmail());
        if(!checkInstructor.isPresent()){
            return learnerRepository.save(learner);
        }
        throw new RecordAlreadyExistsException("This email is already exists.");
    }

    @Override
    public Learner updateLearner(Long id, Learner newLearner) {
        Learner existingLearner = getLearnerById(id);
        if (existingLearner != null) {
            existingLearner.setAge(newLearner.getAge());
            existingLearner.setEmail(newLearner.getEmail());
            existingLearner.setFirstName(newLearner.getFirstName());
            existingLearner.setLastName(newLearner.getLastName());
            return learnerRepository.save(existingLearner);
        }
        throw new RecordAlreadyExistsException("failed to update");
    }

    @Override
    public Learner deleteLearner(Long id) {
        Learner learner=getLearnerById(id);
        this.learnerRepository.delete(learner);
        return learner;
    }
}
