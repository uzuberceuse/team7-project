package sparta.AIBusinessProject.domain.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreListResponseDto {

    private UUID category_id;
    private String StoreName;
    private String location;
}
