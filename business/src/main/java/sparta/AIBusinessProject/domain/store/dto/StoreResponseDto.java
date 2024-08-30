package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.sql.Timestamp;

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
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;


    public static StoreResponseDto toResponseDto(Store store) {
        return new StoreResponseDto(
                store.getCategory().getCategoryName(),
                store.getStoreName(),
                store.getTime(),
                store.getLocation(),
                store.getPhone(),
                store.getDetails(),
                store.getCreated_at(),
                store.getCreated_by(),
                store.getUpdated_at(),
                store.getUpdated_by(),
                store.getDeleted_at(),
                store.getDeleted_by()
        );
    }
}
