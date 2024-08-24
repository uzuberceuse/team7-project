package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class UserResponse {
    private String accessToken;

    public static UserResponse of(String accessToken){
        return UserResponse.builder().accessToken(accessToken).build();
    }
}
