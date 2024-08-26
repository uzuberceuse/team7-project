package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainResponseDto {

    // 고객센터 신고상세조회 (신고 내용 및 답변 정보를 포함)
    private UUID id;
    private String userName;
    private UUID reviewId;
    private String content;
    private String answer;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;
}
