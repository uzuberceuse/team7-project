package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    private UUID id;
    private UUID userId;
    private Timestamp createAt;
    private String createdBy;

}
