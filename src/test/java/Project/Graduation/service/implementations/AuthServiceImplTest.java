package Project.Graduation.service.implementations;

import Project.Graduation.security.JWTUtil;
import Project.Graduation.model.LoginCredentials;
import Project.Graduation.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.Assert.*;

public class AuthServiceImplTest {
    private AuthServiceImpl authServiceImpl;
    private UserRepository userRepository;

    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private  RedisTemplate<String, Object> redisTemplate;
    @BeforeEach
    public void setUp(){
        userRepository = Mockito.mock(UserRepository.class);
        jwtUtil=Mockito.mock(JWTUtil.class);
        authenticationManager=Mockito.mock(AuthenticationManager.class);
        passwordEncoder=Mockito.mock(PasswordEncoder.class);
        redisTemplate=Mockito.mock(RedisTemplate.class);
        authServiceImpl=new AuthServiceImpl(userRepository,jwtUtil,authenticationManager,passwordEncoder,redisTemplate);
    }
    @Test
    void shouldReturnToken_whenLoginHandler(){
        String email="nurhayatk@gmail.com";
        String password="12345";
        LoginCredentials loginCredentials=new LoginCredentials(email,password);
        Map<String, Object> result = authServiceImpl.loginHandler(loginCredentials);
        assertTrue(result.containsKey("jwt-token"));
        String token = (String) result.get("jwt-token");
        //assertTrue(jwtUtil.validateTokenAndRetrieveSubject(token));
        //UserSession userSession = (UserSession) redisTemplate.opsForValue().get(userSession.getSessionId());
        //assertNotNull(userSession);
        //assertEquals(email, userSession.getEmail());
    }

    @AfterEach
    public void tearDown(){

    }

}
