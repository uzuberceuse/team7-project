package sparta.AIBusinessProject.domain.report.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.report.dto.ReportListResponseDto;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.dto.ReportResponseDto;
import sparta.AIBusinessProject.domain.report.entity.Report;
import sparta.AIBusinessProject.domain.report.repository.ReportRepository;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.review.repository.ReviewRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    // 리뷰후기 신고등록(작성)
    @Transactional
    public Report createReport(UUID review_id, UUID user_id, ReportRequestDto requestDto) {
             User user = userRepository.findById(user_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 User를 찾을 수 없습니다."));

            Review review = reviewRepository.findById(review_id)
                    .orElseThrow(()-> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

            Report report = Report.from(requestDto, user, review);

        return reportRepository.save(report);
    }


    // 신고 리스트 조회
    @Transactional
    public List<ReportListResponseDto> getReportList(UUID review_id, UUID user_id) {
        // 특정 리뷰와 사용자에 해당하는 신고를 조회
        return reportRepository.findByReviewIdAndUserId(review_id, user_id).stream()
                .map(report -> ReportListResponseDto.builder()
                        .id(report.getReport_id())
                        .reviewId(report.getReview().getReview_id())
                        .userId(report.getUser().getUser_id())
                        .title(report.getReportTitle())
                        .createdAt(report.getCreated_at())
                        .build())
                .collect(Collectors.toList());
    }
        
    //리뷰후기 신고 삭제
    @Transactional
    public void deleteReport(UUID review_id, UUID user_id) {
        // 특정리뷰와 사용자에 해당하는 모든 신고를 조회하여 삭제
        List<Report> reports = reportRepository.findByReviewIdAndUserId(review_id, user_id);
        if (reports.isEmpty()) {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
        reportRepository.deleteAll(reports);
    }


    }



