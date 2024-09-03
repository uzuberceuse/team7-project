package sparta.AIBusinessProject.domain.report.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.report.dto.ReportListResponseDto;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.entity.Report;
import sparta.AIBusinessProject.domain.report.repository.ReportRepository;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.review.repository.ReviewRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.security.Timestamp;
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
    public Report createReport(UUID review_id, UUID userId, ReportRequestDto requestDto,String createBy) {
             User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 User를 찾을 수 없습니다."));

            Review review = reviewRepository.findById(review_id)
                    .orElseThrow(()-> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다."));
            Report report = Report.from(requestDto, user, review);

            report.setCreated_by(createBy);

        return reportRepository.save(report);
    }


    // 신고 조회
    @Transactional
    public List<ReportListResponseDto> getReportDetail(UUID review_id, UUID userId) {
        // 특정 리뷰와 사용자에 해당하는 신고를 조회
        Review review = reviewRepository.findById(review_id).orElseThrow();

        User user = userRepository.findById(userId).orElseThrow();

        return reportRepository.findByReviewAndUser(review, user).stream()
                .map(report -> ReportListResponseDto.builder()
                        .id(report.getReportId())
                        .reviewId(report.getReview().getReviewId())
                        .userId(report.getUser().getUser_id())
                        .title(report.getReportTitle())
                        .createdAt(report.getCreated_at())
                        .build())
                .collect(Collectors.toList());
    }
        
    //리뷰후기 신고 삭제
    @Transactional
    public void deleteReport(UUID review_id, UUID user_id, String deletedBy) {
        Review review = reviewRepository.findById(review_id).orElseThrow();
        User user = userRepository.findById(user_id).orElseThrow();
        List<Report> reports = reportRepository.findByReviewAndUser(review, user);

        if (reports.isEmpty()) {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
        // 각 Report에 deletedBy 설정
        for (Report report : reports) {
            report.setDeleted_by(deletedBy);
//            report.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        }

        reportRepository.saveAll(reports);  // 변경된 상태 저장

        reportRepository.deleteAll(reports);  // 신고 삭제
    }


    }



