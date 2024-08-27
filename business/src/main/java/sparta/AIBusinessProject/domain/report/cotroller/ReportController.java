package sparta.AIBusinessProject.domain.report.cotroller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.dto.ReportResponseDto;
import sparta.AIBusinessProject.domain.report.service.ReportService;

import java.util.UUID;

@RestController
@RequestMapping("/api/store/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 리뷰후기 신고등록(작성)
    @PostMapping("/{review_id}/{user_id}")
    public ResponseEntity<ReportResponseDto> createReport(
            @PathVariable("review_id") UUID review_id,
            @PathVariable("user_id") UUID user_id,
            @RequestBody ReportRequestDto requestDto){

        ReportResponseDto responseDto = ReportService.createReport(review_id, user_id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 리뷰후기 신고조회

    // 리뷰후기 신고삭제
}
