package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreListResponseDto {

    private UUID categoryId;
    private String StoreName;
    private String location;
    private List<Product> products;

    public StoreListResponseDto(Store store) {
        this.categoryId=store.getCategory().getCategoryId();
        this.StoreName=store.getStoreName();
        this.location=store.getLocation();
        this.products=store.getProducts();
    }
}
