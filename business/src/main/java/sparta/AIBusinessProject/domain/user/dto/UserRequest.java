package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;

// 회원정보 수정을 위한 본인 정보 요청 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String id;
}
