package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    private UUID complainId;
    private UUID userId;
    private Timestamp createdAt;
    private String created_by;

}
