package sparta.AIBusinessProject.domain.notice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
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

    // NoticeRequestDto를 받아 빌더를 통해 Notice 객체를 생성
    public static Notice from(NoticeRequestDto requestDto) {
        return Notice.builder()
                .noticeTitle(requestDto.getNoticeTitle())
                .noticeContent(requestDto.getNoticeContent())
                .createdBy(requestDto.getCreatedBy())
                .updatedBy(requestDto.getUpdateBy())
                .build();
    }


}

