package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    // 고객센터 신고목록조회
    private UUID id;
    private String userName;
    private UUID reviewId;
    private Timestamp createAt;
    private String createdBy;

}
