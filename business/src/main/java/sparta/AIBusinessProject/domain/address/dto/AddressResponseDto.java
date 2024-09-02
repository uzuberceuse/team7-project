package sparta.AIBusinessProject.domain.address.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponseDto;
import sparta.AIBusinessProject.domain.category.entity.Category;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class AddressResponseDto {
    private String name;
    private String zipcode;

    public static AddressResponseDto of(final Address address) {
        return AddressResponseDto.builder()
                .name(address.getAddressName())
                .build();
    }
}
