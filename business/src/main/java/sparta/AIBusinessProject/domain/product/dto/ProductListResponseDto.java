package sparta.AIBusinessProject.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponseDto {

    private Long product_id;
    private String productName;
    private String created_by;
    private boolean status;
}
