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


    private UUID product_id;
    private String productName;
    private boolean status;

    public static ProductListResponseDto of(final Product product) {
        return ProductListResponseDto.builder()
                .product_id(product.getProduct_id())
                .productName(product.getProductName())
                .status(product.isStatus())
                .build();
    }
}
    // Serializable 직렬화 인터페이스를 사용하는 이유를 잘 모르겠다.
    // 사용하지 않는다면?