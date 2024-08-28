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
    private UUID id;
    private UUID userId;
    private UUID reviewId;
    private String content;
    private String createdBy;
    private String deletedBy;

}
