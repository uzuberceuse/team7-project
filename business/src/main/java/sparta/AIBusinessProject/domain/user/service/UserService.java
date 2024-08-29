package sparta.AIBusinessProject.domain.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.user.dto.*;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.entity.UserRoleEnum;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;
import sparta.AIBusinessProject.global.jwt.JwtUtil;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${token.owner}")
    private String ownerToken;
    @Value("${token.manager}")
    private String managerToken;
    @Value("${token.maswer}")
    private String masterToken;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    // 로그아웃 - 구현안됨
    public void logout(User user) {}


    // 회원 존재 여부 검증 비즈니스 로직
    public Boolean verifyUser(final String username) {
        // email 로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findByUsername(username).isPresent();
    }


    // 회원가입
    public void signUp(SignUpRequestDto request) {
        // username 중복확인
        String username=request.getUsername();
        Optional<Object> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // password 암호화
        String password=passwordEncoder.encode(request.getPassword());

        // phone
        String phone=request.getPhone();

        // email 중복확인
        String email=request.getEmail();
        if(userRepository.findByUserEmail(email).isPresent()){
            throw new IllegalArgumentException("email 중복");
        }

        // 사용자 role 확인
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (request.getRole().equals("ROLE_OWNER")) {
            if (!ownerToken.equals(request.getToken())) {
                throw new IllegalArgumentException("not Owner");
            }
            role = UserRoleEnum.OWNER;
        }
        if (request.getRole().equals("ROLE_MANAGER")) {
            if (!managerToken.equals(request.getToken())) {
                throw new IllegalArgumentException("not Manager");
            }
            role = UserRoleEnum.MANAGER;
        }
        if (request.getRole().equals("ROLE_MASTER")) {
            if (!masterToken.equals(request.getToken())) {
                throw new IllegalArgumentException("not MASTER");
            }
            role = UserRoleEnum.MASTER;
        }
        User user=User.create(username,email,password,phone,role);
        userRepository.save(user);
    }


    // 유저 탈퇴 비즈니스 로직
    public Boolean deleteUser(UUID userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User not found"));
        user.changeDeleted(user.getUsername());
        userRepository.save(user);
        return true;
    }

    // 유저 단건 조회 비즈니스 로직
    public UserResponseDto getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Object user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // UserResponseDto로 변환하여 반환
        return UserResponseDto.of((User) user);
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
