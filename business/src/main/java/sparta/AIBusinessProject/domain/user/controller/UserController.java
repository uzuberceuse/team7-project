package sparta.AIBusinessProject.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.user.dto.SignInRequest;
import sparta.AIBusinessProject.domain.user.dto.SignUpRequest;
import sparta.AIBusinessProject.domain.user.dto.UserResponse;
import sparta.AIBusinessProject.domain.user.service.UserService;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    // 회원가입
//    @PostMapping("/signUp")
//    public ResponseEntity<UserResponse> createUser(@RequestBody SignUpRequest request){
//        //userService.createUser(request);
//        return null;
//    }
//
//    // 회원수정
//    @PatchMapping("/{user_id}")
//
//    // 회원 탈퇴
//    @DeleteMapping("")
//
//    // 로그인
//    @GetMapping("/signIn")
//    public ResponseEntity<UserResponse> createAuthenticationToken(@RequestBody SignInRequest request){
//        UserResponse response=userService.createAccessToken(request);
//        return ResponseEntity.ok(response);
//    }
//
//    // 로그아웃
//
//    // 회원 단건조회
//
//    // 회원 목록조회

}
