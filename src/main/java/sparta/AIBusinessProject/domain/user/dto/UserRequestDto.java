package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.address.entity.Address;

import java.util.List;

// 회원정보 수정을 위한 본인 정보 요청 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private String phone;
//    private List<Address> address;
}
