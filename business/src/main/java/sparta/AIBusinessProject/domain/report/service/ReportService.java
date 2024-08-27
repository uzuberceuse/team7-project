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
    public Report createReport(UUID reviewId, UUID userId, ReportRequestDto requestDto) {
             User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 User를 찾을 수 없습니다."));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));

        Report report = Report.from(requestDto, user, review);

        return reportRepository.save(report);
    }


    // 신고 리스트 조회
    @Transactional
    public List<ReportListResponseDto> getReportList(UUID reviewId, UUID userId) {
        return reportRepository.findByReviewIdAndUserId(reviewId, userId).stream()
                .map(report -> ReportListResponseDto.builder()
                        .id(report.getId())
                        .reviewId(UUID.fromString(report.getReview().getReview_id()))
                        .userId(report.getUser().getUser_id())
                        .title(report.getTitle())
                        .createdAt(report.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
        
    //리뷰후기 삭제
    @Transactional
    public void deleteReport(UUID reviewId, UUID userId) {
        List<Report> reports = reportRepository.findByReviewIdAndUserId(reviewId, userId);
        if (reports.isEmpty()) {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
        reportRepository.deleteAll(reports);
    }


    }


}
