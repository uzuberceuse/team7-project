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
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;


    public NoticeResponseDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.createdAt = notice.getCreatedAt();
        this.created_by = notice.getCreated_by();
        this.updated_at = notice.getUpdated_at();
        this.updated_by = notice.getUpdated_by();
        this.deleted_at = notice.getDeleted_at();
        this.deleted_by = notice.getDeleted_by();
    }

}
