package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.complain.entity.Complain;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainResponseDto {

    private UUID complainId;
    private UUID userId;
    private String complainContent;
    private String answer;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;


    public ComplainResponseDto(Complain complain) {
        this.complainId = complain.getComplain_id();
        this.userId = complain.getUser().getUser_id();
        this.complainContent = complain.getComplainContent();
        this.answer = complain.getAnswer();
        this.createdAt = complain.getCreatedAt();
        this.createdBy = complain.getCreatedBy();
        this.updatedAt = complain.getUpdatedAt();
        this.updatedBy = complain.getUpdatedBy();
        this.deletedAt = complain.getDeletedAt();
        this.deletedBy = complain.getDeletedBy();
    }
}
