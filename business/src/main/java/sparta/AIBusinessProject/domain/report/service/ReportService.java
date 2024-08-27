package sparta.AIBusinessProject.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.report.dto.ReportResponseDto;
import sparta.AIBusinessProject.domain.report.entity.Report;
import sparta.AIBusinessProject.domain.report.repository.ReportRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    // 리뷰후기 신고등록(작성)
    public ReportResponseDto createReport(UUID review_id, UUID user_id, ReportRequestDto requestDto) {
        Report report = reportRepository.save(new Report(review_id, user_id,requestDto));
        return null;
    }
}
