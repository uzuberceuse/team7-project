package sparta.AIBusinessProject.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.user.entity.UserRoleEnum;

// 회원 가입 요청 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private UserRoleEnum role;
}
