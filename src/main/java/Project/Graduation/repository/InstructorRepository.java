package Project.Graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Project.Graduation.model.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
