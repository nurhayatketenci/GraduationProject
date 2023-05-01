package Project.Graduation.service.implementations;

import Project.Graduation.exception.RecordAlreadyExistsException;
import Project.Graduation.security.JWTUtil;
import Project.Graduation.model.LoginCredentials;
import Project.Graduation.model.User;
import Project.Graduation.model.UserSession;
import Project.Graduation.repository.UserRepository;
import Project.Graduation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;
     private final RedisTemplate<String, Object> redisTemplate;
    private static final long SESSION_TIMEOUT_SECONDS = 60 * 30; // 30 dakika
    public AuthServiceImpl(UserRepository userRepository, JWTUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


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
            throw new RecordAlreadyExistsException("This email is already registered.");
        }
    }

    @Override
    public Map<String, Object> loginHandler(LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail());

            UserSession userSession = new UserSession(UUID.randomUUID().toString(), body.getEmail());
            redisTemplate.opsForValue().set(userSession.getSessionId(), userSession, SESSION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            return Collections.singletonMap("jwt-token", token);

        }catch (AuthenticationException authExc){

            throw new RuntimeException("Invalid Login Credentials");

        }
    }
}
