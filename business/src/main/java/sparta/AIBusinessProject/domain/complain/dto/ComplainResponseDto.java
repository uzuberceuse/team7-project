package sparta.AIBusinessProject.domain.complain.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.complain.entity.Complain;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainResponseDto {

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

    // 페이징 관련 필드 추가
    private int page;           // 현재 페이지 번호
    private int size;           // 페이지 크기
    private int totalPages;      // 전체 페이지 수
    private long totalElements;  // 전체 요소 수

    public ComplainResponseDto(Complain complain) {
        this.id = complain.getId();
        this.userName = complain.getUserName();
        this.content = complain.getContent();
        this.answer = complain.getAnswer();
        this.createdAt = complain.getCreatedAt();
        this.createdBy = complain.getCreatedBy();
        this.updatedAt = complain.getUpdatedAt();
        this.updatedBy = complain.getUpdatedBy();
        this.deletedAt = complain.getDeletedAt();
        this.deletedBy = complain.getDeletedBy();
    }
}
