package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreListResponseDto {

    private UUID categoryId;
    private String StoreName;
    private String location;

    public StoreListResponseDto(Store store) {
        this.categoryId=store.getCategory().getCategoryId();
        this.StoreName=store.getStoreName();
        this.location=store.getLocation();
    }
}
