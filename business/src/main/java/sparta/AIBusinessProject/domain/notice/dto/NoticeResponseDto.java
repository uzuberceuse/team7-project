package sparta.AIBusinessProject.domain.notice.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.notice.entity.Notice;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {

    private UUID notice_id;
    private String noticeTitle;
    private String noticeContent;
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;


    public NoticeResponseDto(Notice notice) {
        this.notice_id = notice.getNotice_id();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.created_at = notice.getCreated_at();
        this.created_by = notice.getCreated_by();
        this.updated_at = notice.getUpdated_at();
        this.updated_by = notice.getUpdated_by();
        this.deleted_at = notice.getDeleted_at();
        this.deleted_by = notice.getDeleted_by();
    }

}
