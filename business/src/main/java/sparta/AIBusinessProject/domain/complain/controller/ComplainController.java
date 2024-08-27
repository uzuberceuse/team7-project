package sparta.AIBusinessProject.domain.complain.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainRequestDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainResponseDto;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.complain.service.ComplainService;

import java.util.UUID;

@RestController
@RequestMapping("/api/complain")
@RequiredArgsConstructor
public class ComplainController {

    private final ComplainService complainService;

    // 고객센터 신고접수 role을 고객, 관리자만
    @PostMapping("/{user_id}")
    public Complain createComplain(@PathVariable UUID userId, @RequestBody ComplainRequestDto requestDto){
        return complainService.createComplain(userId, requestDto);
    }

    // 고객센터 신고 삭제 role을 관리자 고객만
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Complain> deleteComplain(@PathVariable UUID userId, @RequestParam String deleteBy){
        complainService.deleteReport(userId, deleteBy);
        return ResponseEntity.noContent().build();
    }
    
    // 고객센터 신고 상세조회 role : 관리자 가게주인 고객
    @GetMapping("/{user_id}")
    public ResponseEntity<ComplainResponseDto> getComplainDetail(@PathVariable UUID userId){
        ComplainResponseDto responseDto = complainService.getComplainDetail(userId);
        return ResponseEntity.ok(responseDto);

    }
    
//    // 고객센터 신고 목록조회 role : 관리자 가게주인 고객
//    @GetMapping
//    public ResponseEntity<Page<ComplainListResponseDto>> getComplainList(
//            @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
//            @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
//            @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
//            @RequestParam(defaultValue = "desc") String direction    // 기본 정렬 방향 desc
//    ) {
//        // 페이지 요청 생성
//        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
//
//        // 페이징된 고객센터 신고 목록조회
//        Page<ComplainListResponseDto> complainList = complainService.getComplainList(pageable);
//
//        return ResponseEntity.ok(complainList);
//    }



}
