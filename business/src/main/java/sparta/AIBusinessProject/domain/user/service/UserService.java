package sparta.AIBusinessProject.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.user.dto.SignInRequestDto;
import sparta.AIBusinessProject.domain.user.dto.SignInResponseDto;
import sparta.AIBusinessProject.domain.user.dto.SignUpRequestDto;
import sparta.AIBusinessProject.domain.user.dto.UserResponseDto;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import javax.crypto.SecretKey;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    public UserResponseDto getUser(Long id, User user) {
        return null;
    }

    public List<UserResponseDto> getUserList(User user) {
        return null;
    }

    public UserResponseDto updateProduct(Long id, SignUpRequestDto request) {
        return null;
    }
}
