package sparta.AIBusinessProject.domain.notice.dto;


import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeRequestDto {
    private String noticeTitle;
    private String noticeContent;
    private String created_by; // 작성자
    private String update_by; // 수정자
    private String delete_by; // 삭제자
}
