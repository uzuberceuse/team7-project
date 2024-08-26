package sparta.AIBusinessProject.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    // 목록조회
    private UUID id;
    private String noticeTitle;
    private Timestamp createAt;
    private String createBy;
}
