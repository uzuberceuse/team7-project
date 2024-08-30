package sparta.AIBusinessProject.domain.store.dto;


import lombok.*;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequestDto {

    private UUID categoryId;
    private String storeName;
    private String time;
    private String location;
    private String phone;
    private String details;
}
