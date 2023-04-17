package Project.Graduation.controller;

import Project.Graduation.model.LoginCredentials;
import Project.Graduation.model.User;
import Project.Graduation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user){
        return authService.registerHandler(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginCredentials loginCredentials){
        return authService.loginHandler(loginCredentials);
    }



}
