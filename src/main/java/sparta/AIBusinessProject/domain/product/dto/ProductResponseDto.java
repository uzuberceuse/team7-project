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
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;

    // ProductResponseDTO 변환 메서드
    public static ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getDetails(),
                product.getPrice(),
                product.isStatus(),
                product.getCreatedAt(),
                product.getCreatedBy(),
                product.getUpdatedAt(),
                product.getUpdatedBy(),
                product.getCreatedAt(),
                product.getDeletedBy()
        );
    }
}
