package sparta.AIBusinessProject.domain.address.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class AddressResponse {
    private String name;
    private String zipcode;
}
