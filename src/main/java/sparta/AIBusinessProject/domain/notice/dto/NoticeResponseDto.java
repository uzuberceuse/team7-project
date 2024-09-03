package sparta.AIBusinessProject.domain.notice.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.notice.entity.Notice;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponseDto {

    private UUID noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;


    public NoticeResponseDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.createdAt = notice.getCreatedAt();
        this.createdBy = notice.getCreatedBy();
        this.updatedAt = notice.getUpdatedAt();
        this.updatedBy = notice.getUpdatedBy();
        this.deletedAt = notice.getDeletedAt();
        this.deletedBy = notice.getDeletedBy();
    }

}
