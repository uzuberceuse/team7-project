package sparta.AIBusinessProject.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.security.UserDetailsImpl;
import sparta.AIBusinessProject.domain.user.dto.SignInRequestDto;
import sparta.AIBusinessProject.domain.user.dto.SignInResponseDto;
import sparta.AIBusinessProject.domain.user.dto.SignUpRequestDto;
import sparta.AIBusinessProject.domain.user.dto.UserResponseDto;
import sparta.AIBusinessProject.domain.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> createUser(@RequestBody SignUpRequestDto request){
        Boolean response=userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    // 회원수정
    @PatchMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @RequestBody SignUpRequestDto request){
        return userService.updateProduct(id,request);
    }


    // 회원 탈퇴
    @DeleteMapping("/{user_id}")


    // 로그인
    @GetMapping("/signIn")
    public SignInResponseDto createAuthenticationToken(@RequestBody SignInRequestDto request){
        SignInResponseDto response=userService.createAccessToken(request);
        return response;
    }

    // userId 존재여부 검증 API
    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyUser(final @RequestParam(value = "email") String email) {
        Boolean response = userService.verifyUser(email);
        return ResponseEntity.ok(response);
    }

    // 로그아웃


    // 회원 단건조회
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUser(id,userDetails.getUser());
    }

    // 회원 목록조회
    @GetMapping("/")
    public List<UserResponseDto> getUserList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUserList(userDetails.getUser());
    }
}
