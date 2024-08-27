package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainListResponseDto {

    private UUID id;
    private UUID userId;
    private String createdBy;
    private Timestamp createAt;

}
