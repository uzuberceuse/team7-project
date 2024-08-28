package sparta.AIBusinessProject.domain.notice.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "p_notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id
    @GeneratedValue
    @Column(name = "notice_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String noticeTitle;

    private String noticeContent;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;

    public Notice(NoticeRequestDto requestDto) {

    }
}

