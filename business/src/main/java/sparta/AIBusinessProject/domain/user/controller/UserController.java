package sparta.AIBusinessProject.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.user.dto.*;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;
import sparta.AIBusinessProject.domain.user.service.UserService;

import java.util.List;
import java.util.UUID;

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
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable UUID userId,
            @RequestBody UserRequestDto request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자의 ID와 수정 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserResponseDto response = userService.updateUser(userId,request,userDetails.getUser().getUsername());
        return ResponseEntity.ok(response);
    }


    // 회원 탈퇴
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자의 ID와 탈퇴 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

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
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.logout(userDetails.getUser());
        return ResponseEntity.ok("Logout successfully.");
    }

    // 회원 단건조회
    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        // 로그인한 사용자의 ID와 조회 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        UserResponseDto user=userService.getUser(user_id);
        return ResponseEntity.ok(user);
    }

    // 회원 목록조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUserList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        // 예시: 관리자 권한이 있을 때만 전체 목록 조회 허용
        if (!userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("MANAGER")||auth.getAuthority().equals("MASTER"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<UserResponseDto> users=userService.getUserList(userDetails.getUser());
        return ResponseEntity.ok(users);
    }



}
