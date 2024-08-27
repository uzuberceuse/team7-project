package sparta.AIBusinessProject.domain.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequestDto {
    private String name;
    private String zipcode;
}
