package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;

// 로그인 반환 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class SignInResponse {
    private String accessToken;

    public static SignInResponse of(String accessToken) {
        return SignInResponse.builder().accessToken(accessToken).build();
    }
}
