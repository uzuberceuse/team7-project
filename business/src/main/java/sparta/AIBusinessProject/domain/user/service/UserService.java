package sparta.AIBusinessProject.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.user.dto.SignInRequestDto;
import sparta.AIBusinessProject.domain.user.dto.SignInResponseDto;
import sparta.AIBusinessProject.domain.user.dto.SignUpRequestDto;
import sparta.AIBusinessProject.domain.user.dto.UserResponseDto;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;
import sparta.AIBusinessProject.global.config.PasswordConfig;
import sparta.AIBusinessProject.global.jwt.JwtUtil;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    //private final SecretKey secretKey;


    // 회원 존재 여부 검증 비즈니스 로직
    public Boolean verifyUser(final String email) {
        // email 로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findByUserEmail(email).isPresent();
    }


    public Boolean createUser(SignUpRequestDto request) {
        return null;
    }


    public SignInResponseDto createAccessToken(SignInRequestDto request) {
        return null;
    }

    public Boolean deleteUser(Long userId) {
        return null;
    }

    public UserResponseDto getUser(UUID id, User user) {
        return null;
    }

    public List<UserResponseDto> getUserList(User user) {
        return null;
    }

    public UserResponseDto updateUser(UUID id, SignUpRequestDto request) {
        return null;
    }


    public void logout(User user) {
    }
}
