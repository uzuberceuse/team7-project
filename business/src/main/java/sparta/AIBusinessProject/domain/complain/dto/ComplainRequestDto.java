package sparta.AIBusinessProject.domain.complain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainRequestDto {

    // 신고접수 및 삭제
    private UUID id;          // 신고 삭제 시 사용될 신고의 ID
    private String userName;
    private UUID reviewId;
    private String content;   // 신고 접수 시 사용
    private String createdBy;
    private String deletedBy; // 신고 삭제 시 사용될 삭제 요청자

}
