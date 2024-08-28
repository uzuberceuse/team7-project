package sparta.AIBusinessProject.domain.report.cotroller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.report.dto.ReportListResponseDto;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.dto.ReportResponseDto;
import sparta.AIBusinessProject.domain.report.entity.Report;
import sparta.AIBusinessProject.domain.report.service.ReportService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/store/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 리뷰후기 신고등록(작성) (Role : 관리자 고객)
    @Secured({"ROLE_MANAGER", "ROLE_CUSTOMER"})
    @PostMapping("/{review_id}/{user_id}")
    public Report createReport(
            @PathVariable("review_id") UUID reviewId,
            @PathVariable("user_id") UUID userId,
            @RequestBody ReportRequestDto requestDto){
        return reportService.createReport(reviewId, userId, requestDto);
    }

    // 리뷰후기 신고조회 (Role : 관리자 가게주인 고객)
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @GetMapping("/{review_id}/{user_id}")
    public List<ReportListResponseDto> getReportList(
            @PathVariable("review_id") UUID review_id,
            @PathVariable("user_id") UUID user_id){
        return reportService.getReportList(review_id, user_id);
    }

    // 리뷰후기 신고삭제 (Role : 관리자 가게주인 고객)
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @DeleteMapping("/{review_id}/{user_id}")
    public void deleteReport(
            @PathVariable("review_id") UUID reviewId,
            @PathVariable("user_id") UUID userId) {
        reportService.deleteReport(reviewId, userId);
    }
}
