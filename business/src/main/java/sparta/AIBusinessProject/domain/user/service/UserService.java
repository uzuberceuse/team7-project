package sparta.AIBusinessProject.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.user.dto.*;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.entity.UserRoleEnum;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;
import sparta.AIBusinessProject.global.config.PasswordConfig;
import sparta.AIBusinessProject.global.jwt.JwtUtil;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public SignInResponseDto createAccessToken(SignInRequestDto request) {
        return null;
    }

    public void logout(User user) {}


    // 회원 존재 여부 검증 비즈니스 로직
    public Boolean verifyUser(final String email) {
        // email 로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findByUserEmail(email).isPresent();
    }

    public User signUp(SignUpRequestDto request) {
        // username 중복확인
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new IllegalArgumentException("username 중복");
        }
        // password 암호화
        String password=passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        // email 중복확인
        if(userRepository.findByUserEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("email 중복");
        }

        // 사용자 role 확인
        UserRoleEnum role=UserRoleEnum.OWNER;
        if(request.getRole().equals("OWNER")){

        }
    }


    // 유저 탈퇴 비즈니스 로직
    public Boolean deleteUser(UUID userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User not found"));
        user.chanageDeleted(user.getUsername());
        userRepository.save(user);
        return true;
    }

    // 유저 단건 조회 비즈니스 로직
    public UserResponseDto getUser(UUID userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User not found"));
        return UserResponseDto.of(user);
    }


    // 유저 목록 조회 비즈니스 로직
    public List<UserResponseDto> getUserList(User user) {
        return userRepository.findAll().stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }

    // 유저 수정 비즈니스 로직
    public UserResponseDto updateUser(
            UUID id,
            UserRequestDto request,
            String updatedBy
            ) {
        // 기존 유저 조회
        User user=userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("user not found"));
        // 업데이트
        user.changeUpdated(request,updatedBy);
        // 변경사항 저장
        userRepository.save(user);
        return UserResponseDto.of(user);
    }
}
