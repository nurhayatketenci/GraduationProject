package Project.Graduation.service;

import Project.Graduation.model.LoginCredentials;
import Project.Graduation.model.User;

import java.util.Map;

public interface AuthService {
    Map<String, Object> registerHandler( User user);
    Map<String, Object> loginHandler( LoginCredentials body);
}
