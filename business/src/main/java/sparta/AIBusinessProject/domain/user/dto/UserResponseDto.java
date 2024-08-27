package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.address.entity.Address;

import java.util.List;

//회원정보 수정 후 반환 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class UserResponseDto {
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private List<Address> address;
}
