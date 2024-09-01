package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

//회원정보 수정 후 반환 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class UserResponseDto {
    private String username;
    private String email;
    private String password;
    private String phone;
    private List<Address> address;

    public static UserResponseDto of(final User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .build();
    }


}
