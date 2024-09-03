package sparta.AIBusinessProject.domain.notice.controller;


import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.notice.dto.NoticeListResponseDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeResponseDto;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.service.NoticeService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록 (Role : 관리자)
    @Secured("ROLE_MANAGER")
    @PostMapping
    public ResponseEntity<NoticeResponseDto> createNotice(@RequestBody NoticeRequestDto requestDto,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String createBy = userDetails.getUsername();
        NoticeResponseDto responseDto = noticeService.createNotice(requestDto,createBy);
        return ResponseEntity.ok(responseDto);
    }

    // 공지사항 수정 (Role : 관리자)
    @Secured("ROLE_MANAGER")
    @PatchMapping("/{notice_id}")
    public ResponseEntity<NoticeResponseDto> updateNotice(
            @PathVariable UUID notice_id,
            @RequestBody NoticeRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 수정할 데이터를 가져온다.

        String updateBy = userDetails.getUsername();
        NoticeResponseDto responseDto = noticeService.updateNotice(notice_id, requestDto,updateBy);
        return ResponseEntity.ok(responseDto); // 수정된 공지사항 정보

    }

    // 공지사항 삭제 (Role : 관리자)
    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{notice_id}")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable UUID notice_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String deleteBy = userDetails.getUsername();  // 인증된 사용자 이름 가져오기
        noticeService.deleteNotice(notice_id, deleteBy);
        return ResponseEntity.noContent().build();
    }



    // 공지사항 상세조회 (Role : 관리자 고객 가게주인)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{notice_id}")
    public ResponseEntity<NoticeResponseDto> getNoticeDetail(@PathVariable UUID notice_id) {
        NoticeResponseDto responseDto = noticeService.getNoticeDetail(notice_id);
        return ResponseEntity.ok(responseDto);

    }

    // 공지사항 List 조회 (Role : 관리자 고객 가게주인)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<Page<NoticeListResponseDto>> getNoticeList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy, // 카멜 케이스로 변경
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<NoticeListResponseDto> noticeList = noticeService.getNoticeList(pageable);

        return ResponseEntity.ok(noticeList);
    }
}




