package Project.Graduation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import Project.Graduation.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


} 
