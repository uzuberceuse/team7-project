package sparta.AIBusinessProject.domain.complain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainRequestDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainResponseDto;
import sparta.AIBusinessProject.domain.complain.service.ComplainService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.util.UUID;

@RestController
@RequestMapping("/api/complain")
@RequiredArgsConstructor
public class ComplainController {

    private final ComplainService complainService;

    // 고객센터 신고접수 role을 고객, 관리자만
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @PostMapping("/{user_id}")
    public ResponseEntity<ComplainResponseDto> createComplain(
            @PathVariable("user_id") UUID user_id,
            @RequestBody ComplainRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ComplainResponseDto responseDto = complainService.createComplain(user_id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 고객센터 신고 삭제 role을 관리자 고객만
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteComplain(
            @PathVariable("user_id") UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        complainService.deleteComplain(user_id);
        return ResponseEntity.noContent().build();
    }

    // 고객센터 신고 목록조회 role : 관리자 가게주인 고객
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<Page<ComplainListResponseDto>> getComplainList(
             @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
             @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
             @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
             @RequestParam(defaultValue = "desc") String direction ,   // 기본 정렬 방향 desc
             @AuthenticationPrincipal UserDetailsImpl userDetails
              ) {

        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(userDetails.getUser().getUser_id())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // 페이지 요청 생성
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<ComplainListResponseDto> responseDtos = complainService.getComplainList(pageable);
        return ResponseEntity.ok(responseDtos);
    }
    
    // 고객센터 신고 상세조회 role : 관리자 가게주인 고객
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{user_id}")
    public ResponseEntity<ComplainResponseDto> getComplainDetail(
            @PathVariable UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
        if (!userDetails.getUser().getUser_id().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ComplainResponseDto responseDto = complainService.getComplainDetail(user_id);
        return ResponseEntity.ok(responseDto);

    }
}
