package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;

// 로그인 반환 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class SignInResponseDto {
    private String accessToken;

    public static SignInResponseDto of(String accessToken) {
        return SignInResponseDto.builder().accessToken(accessToken).build();
    }
}
