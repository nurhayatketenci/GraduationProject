package Project.Graduation.service.implementations;

import Project.Graduation.Security.JWTUtil;
import Project.Graduation.model.LoginCredentials;
import Project.Graduation.model.User;
import Project.Graduation.repository.UserRepository;
import Project.Graduation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> registerHandler(User user) {
        Optional<User> checkUser=userRepository.findByEmail(user.getEmail());
        if(!checkUser.isPresent()){
            String encodedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPass);
            user = userRepository.save(user);
            String token = jwtUtil.generateToken(user.getEmail());
            return Collections.singletonMap("jwt-token", token);
        }
        else{
            return Collections.singletonMap("jwt-token", "this email is already registered");
        }
    }

    @Override
    public Map<String, Object> loginHandler(LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}
