package sparta.AIBusinessProject.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 로그인 요청 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {
    private String username;
    private String password;
}
