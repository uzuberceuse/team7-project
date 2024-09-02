package sparta.AIBusinessProject.domain.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("email 중복");
        }

        User user=User.create(username,email,password,phone,request.getRole());
        userRepository.save(user);
    }


    // 유저 탈퇴 비즈니스 로직
    public Boolean deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user=userRepository.findById(userDetails.getId())
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


    // Pageable 파라미터를 받아 페이징을 구성하는 방법
    public Page<UserResponseDto> getUserList(Pageable pageable) {
        Page<User> user = userRepository.findByRole(UserRoleEnum.MASTER, pageable);
        List<UserResponseDto> contents = user.getContent().stream().map(UserResponseDto::of).toList();

        return new PageImpl<>(contents, pageable, user.getSize());
    }

    // 유저 수정 비즈니스 로직
    public UserResponseDto updateUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            UserRequestDto request
            ) {
        // 기존 유저 조회
        User user=userRepository.findById(userDetails.getId())
                .orElseThrow(()->new IllegalArgumentException("user not found"));
        // 업데이트
        user.changeUpdated(request,userDetails.getUsername());
        // 변경사항 저장
        userRepository.save(user);
        return UserResponseDto.of(user);
    }


    // 로그아웃 - 구현안됨
    public void logout(User user) {}

}
