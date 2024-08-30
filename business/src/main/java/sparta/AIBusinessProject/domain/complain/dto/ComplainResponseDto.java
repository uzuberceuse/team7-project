package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.complain.entity.Complain;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainResponseDto {

    private UUID complain_id;
    private UUID user_id;
    private UUID review_id;
    private String complainContent;
    private String answer;
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;


    public ComplainResponseDto(Complain complain) {
        this.complain_id = complain.getComplain_id();
        this.user_id = complain.getUser().getUser_id();
        this.complainContent = complain.getComplainContent();
        this.answer = complain.getAnswer();
        this.created_at = complain.getCreated_at();
        this.created_by = complain.getCreated_by();
        this.updated_at = complain.getUpdated_at();
        this.updated_by = complain.getUpdated_by();
        this.deleted_at = complain.getDeleted_at();
        this.deleted_by = complain.getDeleted_by();
    }
}
