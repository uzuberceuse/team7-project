package sparta.AIBusinessProject.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {

    // 리뷰신고상세조회
    private UUID id;
    private UUID reviewId;
    private UUID userId;
    private String createBy;
    private String updatedBy;
    private String deletedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String title;
    private String content;
}
