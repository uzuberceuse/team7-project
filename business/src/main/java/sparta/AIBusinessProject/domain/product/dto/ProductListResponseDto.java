package sparta.AIBusinessProject.domain.product.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.product.entity.Product;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ProductListResponseDto implements Serializable {

    private UUID productId;
    private String productName;
    private boolean status;

    public static ProductListResponseDto of(final Product product) {
        return ProductListResponseDto.builder()
                .productName(product.getProductName())
                .status(product.isStatus())
                .build();
    }
}