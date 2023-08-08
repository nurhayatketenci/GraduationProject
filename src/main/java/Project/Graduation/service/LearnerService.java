package Project.Graduation.service;

import Project.Graduation.model.Learner;


import java.util.List;

public interface LearnerService {
    List<Learner> getAllLearner();
    Learner getLearnerById(Long id);
    Learner addLearner(Learner learner);
    Learner updateLearner(Long id, Learner newLearner);
    Learner deleteLearner(Long id) ;
}
