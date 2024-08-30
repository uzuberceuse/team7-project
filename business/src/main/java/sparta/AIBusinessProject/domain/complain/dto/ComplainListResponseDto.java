package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    private UUID complain_id;
    private UUID user_id;
    private Timestamp created_at;
    private String created_by;

}
