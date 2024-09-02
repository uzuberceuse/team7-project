package sparta.AIBusinessProject.domain.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.user.dto.*;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;
import sparta.AIBusinessProject.domain.user.service.UserService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<String> signup(
            @RequestBody @Valid SignUpRequestDto requestDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            String message=bindingResult.getFieldError().getDefaultMessage();
            log.error(message);
            ResponseEntity.ok(message);

        }
        userService.signUp(requestDto);
        return ResponseEntity.ok("signUp successfully");
    }



    // 회원수정
    @PatchMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("user_id") UUID userId,
            @RequestBody UserRequestDto request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //로그인한 사용자의 ID와 수정 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserResponseDto response = userService.updateUser(userDetails,request);
        return ResponseEntity.ok(response);
    }


    // 회원 탈퇴
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("user_id") UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자의 ID와 탈퇴 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.deleteUser(userDetails);
        return ResponseEntity.noContent().build();
    }

    // 로그인
    @GetMapping("/signIn")
    public SignInResponseDto createAuthenticationToken(@RequestBody SignInRequestDto request){
        return null;
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

        UserResponseDto user=userService.getUser(userDetails);
        return ResponseEntity.ok(user);
    }

    // 회원 목록조회
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getUserList(PageRequest pageable,@AuthenticationPrincipal UserDetailsImpl userDetails){
        // 예시: 관리자 권한이 있을 때만 전체 목록 조회 허용
        if (!userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER")||auth.getAuthority().equals("ROLE_MASTER"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Page<UserResponseDto> response = userService.getUserList(pageable);
        return ResponseEntity.ok(response);
    }
}
