package sparta.AIBusinessProject.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private UUID storeId;
    private String productName;
    private Integer price;
    private String details;
    private boolean status;
}
