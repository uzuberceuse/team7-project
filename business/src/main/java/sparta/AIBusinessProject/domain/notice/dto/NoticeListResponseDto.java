package sparta.AIBusinessProject.domain.notice.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    // 목록조회
    private UUID notice_id;
    private String noticeTitle;
    private Timestamp create_at;
    private String create_by;


}
