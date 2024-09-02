package sparta.AIBusinessProject.domain.user.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.address.entity.Address;

import java.util.List;

// 회원 목록 조회 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserListResponseDto {
    private String username;
    private String phone;
    private List<Address> address;
}
