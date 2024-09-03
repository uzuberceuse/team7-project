package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private String categoryName;
    private String StoreName;
    private String time;
    private String location;
    private String phone;
    private String details;
    private List<Product> products;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;


    public static StoreResponseDto toResponseDto(Store store) {
        return new StoreResponseDto(
                store.getCategory().getCategoryName(),
                store.getStoreName(),
                store.getTime(),
                store.getLocation(),
                store.getPhone(),
                store.getDetails(),
                store.getProducts(),
                store.getCreatedAt(),
                store.getCreatedBy(),
                store.getUpdatedAt(),
                store.getUpdatedBy(),
                store.getDeletedAt(),
                store.getDeletedBy()
        );
    }
}
