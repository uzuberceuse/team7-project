package sparta.AIBusinessProject.domain.notice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.notice.dto.NoticeListResponseDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeResponseDto;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.service.NoticeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록 (Role : 관리자)
    @Secured({"ROLE_MANAGER"})
    @PostMapping
    public ResponseEntity<NoticeResponseDto> createNotice(@RequestBody NoticeRequestDto requestDto) {
        NoticeResponseDto responseDto = noticeService.createNotice(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 공지사항 수정 (Role : 관리자)
    @Secured({"ROLE_MANAGER"})
    @PatchMapping("/{notice_id}")
    public ResponseEntity<NoticeResponseDto> updateNotice(
            @PathVariable UUID notice_id,
            @RequestBody NoticeRequestDto requestDto) { // 수정할 데이터를 가져온다.
        NoticeResponseDto responseDto = noticeService.updateNotice(notice_id, requestDto);
        return ResponseEntity.ok(responseDto); // 수정된 공지사항 정보

    }

    // 공지사항 삭제 (Role : 관리자)
    @Secured({"ROLE_MANAGER"})
    @DeleteMapping
    public ResponseEntity<Notice> deleteNotice(
            @PathVariable UUID notice_id, @RequestParam String deleteBy) {
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
            @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
            @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
            @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
            @RequestParam(defaultValue = "desc") String direction    // 기본 정렬 방향 desc
    ) {
        // 페이지 요청 생성
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        // 페이징된 공지사항 목록 조회
        Page<NoticeListResponseDto> noticeList = noticeService.getNoticeList(pageable);

        return ResponseEntity.ok(noticeList);
    }
}




