package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequestDto {

    private UUID category_id;
    private String storeName;
    private String time;
    private String location;
    private String phone;
    private String details;
}
