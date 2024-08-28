package sparta.AIBusinessProject.domain.notice.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.notice.entity.Notice;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {

    private UUID id;
    private String noticeTitle;
    private String noticeContent;
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


    public NoticeResponseDto(Notice notice) {
        this.id = notice.getId();
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
