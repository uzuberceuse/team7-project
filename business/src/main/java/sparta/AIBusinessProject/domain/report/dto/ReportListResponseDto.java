package sparta.AIBusinessProject.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportListResponseDto {

    // 신고목록 조회
    private UUID id;
    private UUID reviewId;
    private UUID userId;
    private String title;
    private Timestamp createdAt;
}
