package sparta.AIBusinessProject.domain.notice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequestDto {

    private UUID id;
    private String noticeTitle; 
    private String noticeContent;
    private String createdBy; // 작성자
    private String updateBy; // 수정자
    private String deleteBy; // 삭제자
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

}
