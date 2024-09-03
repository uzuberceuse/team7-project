package sparta.AIBusinessProject.domain.report.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.report.dto.ReportListResponseDto;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.entity.Report;
import sparta.AIBusinessProject.domain.report.service.ReportService;
import sparta.AIBusinessProject.domain.user.service.UserService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/store/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    // 리뷰후기 신고등록(작성) (Role : 관리자 고객)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @PostMapping("/{review_id}/{user_id}")
    public ResponseEntity<Report> createReport(
            @PathVariable("review_id") UUID review_id,
            @PathVariable("user_id") UUID user_id,
            @RequestBody ReportRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
        if (!userDetails.getId().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String createBy = userDetails.getUsername();
        Report report = reportService.createReport(review_id, user_id, requestDto, createBy);
        return ResponseEntity.ok(report);
    }

    // 리뷰후기 신고조회 (Role : 관리자 가게주인 고객)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{review_id}/{user_id}")
    public ResponseEntity<List<ReportListResponseDto>> getReportDetail(
            @PathVariable("review_id") UUID review_id,
            @PathVariable("user_id") UUID user_id){

//        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
//        if (!userDetails.getId().equals(user_id)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        List<ReportListResponseDto> reportList = reportService.getReportDetail(review_id, user_id);
        return ResponseEntity.ok(reportList);
    }

    // 리뷰후기 신고삭제 (Role : 관리자 가게주인 고객)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{review_id}/{user_id}")
    public ResponseEntity<Void> deleteReport(
            @PathVariable("review_id") UUID review_id,
            @PathVariable("user_id") UUID user_id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        // 로그인한 사용자의 ID와 요청한 ID가 일치하는지 확인
//        if (!userDetails.getId().equals(user_id)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        String deletedBy = userDetails.getUsername();
        reportService.deleteReport(review_id, user_id, deletedBy);
        return ResponseEntity.noContent().build();
    }
}
