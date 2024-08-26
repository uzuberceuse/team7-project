package sparta.AIBusinessProject.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDto {

    // 목록조회
    private List<NoticeResponseDto> noticeList;
}
