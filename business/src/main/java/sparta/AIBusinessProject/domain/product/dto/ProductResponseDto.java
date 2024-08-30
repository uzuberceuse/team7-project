package sparta.AIBusinessProject.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.product.entity.Product;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private UUID productId;
    private String productName;
    private String details;
    private Integer price;
    private boolean status;
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

    // ProductResponseDTO 변환 메서드
    public static ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getDetails(),
                product.getPrice(),
                product.isStatus(),
                product.getCreated_at(),
                product.getCreated_by(),
                product.getUpdated_at(),
                product.getUpdated_by(),
                product.getDeleted_at(),
                product.getDeleted_by()
        );
    }
}
