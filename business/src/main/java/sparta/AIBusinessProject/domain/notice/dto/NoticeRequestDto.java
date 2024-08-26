package sparta.AIBusinessProject.domain.notice.dto;


import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequestDto {

    // 공지사항 등록 수정
    private UUID id;
    private String noticeTitle; 
    private String noticeContent;
    private String createdBy; // 작성자
    private String updateBy; // 수정자
    private String deleteBy; // 삭제자
//    private Timestamp createdAt;
//    private Timestamp updatedAt;
//    private Timestamp deletedAt;

}
