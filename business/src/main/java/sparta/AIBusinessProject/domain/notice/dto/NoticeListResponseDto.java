package sparta.AIBusinessProject.domain.notice.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.notice.entity.Notice;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    // 목록조회
    private UUID noticeId;
    private String noticeTitle;
    private Timestamp createdAt;
    private String created_by;

    public NoticeListResponseDto(Notice notice) {
        this.noticeId = notice.getNoticeId();
        this.noticeTitle = notice.getNoticeTitle();
        this.createdAt = notice.getCreatedAt(); // 필드 이름 변경 반영
        this.created_by = notice.getCreated_by();
    }


}
